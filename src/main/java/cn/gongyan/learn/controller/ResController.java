/***********************************************************
 * @Description : 资源控制器
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.controller;

import cn.gongyan.learn.beans.entity.ExamRecord;
import cn.gongyan.learn.beans.entity.Question;
import cn.gongyan.learn.beans.entity.Resource;
import cn.gongyan.learn.beans.qo.ResourceQo;
import cn.gongyan.learn.beans.vo.ResourcePageVo;
import cn.gongyan.learn.beans.vo.ResultVO;
import cn.gongyan.learn.service.ExamService;
import cn.gongyan.learn.service.RecordService;
import cn.gongyan.learn.service.ResourceService;
import cn.gongyan.learn.utils.DownLoader;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Res APIs")
@RequestMapping("/res")
public class ResController {
    @Autowired
    ResourceService resourceService;
    @Autowired
    RecordService examRecordService;
    @Autowired
    ExamService examService;

    @GetMapping("/admin/list")
    @ApiOperation("获取所有考试的列表")
    ResultVO<ResourcePageVo> getResList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        ResultVO<ResourcePageVo> resultVO;
        try {
            ResourcePageVo resPageVo = resourceService.getResourceList(pageNo, pageSize);
            resultVO = new ResultVO<>(0, "获取考试列表成功", resPageVo);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO<>(-1, "获取考试列表失败", null);
        }
        return resultVO;
    }


    @GetMapping("/themes")
    public ResultVO<JSONObject> getThemes(){
        ResultVO<JSONObject> resultVO;
        try {
            JSONObject themes = resourceService.getThemes();
            resultVO=new ResultVO<>(0,"获取资源列表成功",themes);
        }catch (Exception e){
            e.printStackTrace();
            resultVO=new ResultVO<>(-1,"获取资源列表错误",null);
        }

        return resultVO;
    }

    @PostMapping("/test/submit/{id}")
    @ApiOperation("学生提交作业")
    public ResultVO<String> sumitTest(@PathVariable("id") String id,@RequestBody List<Question> list,HttpServletRequest request){
        ResultVO<String> resultVO;
        String userId = (String)request.getAttribute("user_id");
        try {
            ExamRecord record = examService.addTestRecord(list, userId, id);
            resultVO=new ResultVO<>(0,"批改作业成功",null);
        }catch (Exception e){
            e.printStackTrace();
            resultVO=new ResultVO<>(-1,"批改作业失败",null);
        }
        return resultVO;
    }


    @GetMapping("/test/get/{id}")
    @ApiOperation("获取相应作业")
    public ResultVO<String> getTest(@PathVariable("id") String id, HttpServletRequest request){
        String userId = (String)request.getAttribute("user_id");
        String testId =id;
        String type="homework";
        ResultVO<String> resultVO;
        try {
            ExamRecord record = examRecordService.getOneRecord(userId, testId, type);
            if(record==null){
                //没有做过这个作业
                Resource resource = resourceService.getOneResource(id);
                if(resource==null){
                    throw new Exception("错误的作业id");
                }
                assert(resource.getResType().compareTo("homework")==0);
                String[] strs = resource.getResUrl().split(",");
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (String s : strs) {
                    arrayList.add(Integer.parseInt(s));
                }
                List<Question> questions = resourceService.getHomeworkQuestions(arrayList);
                resultVO=new ResultVO<>(0,"notDone", JSON.toJSONString(questions));
            }else{
                //做过这个作业
                String download = DownLoader.download(record.getExamOptionUrl());
                System.out.println(download);
                resultVO=new ResultVO<>(1,"hasDone",download);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO=new ResultVO<>(-1,"获取作业错误",null);
        }
        return resultVO;
    }


    @PostMapping("/addRes")
    public ResultVO<String> addRes(@RequestBody  ResourceQo resourceQo,HttpServletRequest request){
        String userId = (String)request.getAttribute("user_id");
        ResultVO<String> resultVO;
        try {
            Integer resource = resourceService.addOneResource(resourceQo, userId);
            if(resource==0){
                throw  new Exception("添加资源出错");
            }
            resultVO=new ResultVO<>(0,"添加资源成功",null);
        }catch (Exception e){
            e.printStackTrace();
            resultVO=new ResultVO<>(-1,"添加资源失败",null);
        }
        return resultVO;
    }

}
