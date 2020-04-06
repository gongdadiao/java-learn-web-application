/***********************************************************
 * @Description : 关于考试完成记录的控制器
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.controller;

import cn.gongyan.learn.beans.entity.ExamRecord;
import cn.gongyan.learn.beans.jsonobject.OneExam;
import cn.gongyan.learn.beans.vo.RecordPageVo;
import cn.gongyan.learn.beans.vo.ResultVO;
import cn.gongyan.learn.service.RecordService;
import cn.gongyan.learn.utils.UpLoader;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Api(tags = "Record APIs")
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @PostMapping("/admin/marked/{id}")
    public ResultVO<String> finishMark(@PathVariable String id,@RequestBody String text){
        ResultVO<String> resultVO;
        try {
            String key = UpLoader.uploadQiniuText("result", text, id);
            OneExam oneExam = JSON.parseObject(text, OneExam.class);
            recordService.UpdateRecord(id,"complete",key,oneExam.getExamFinalScore());
            resultVO=new ResultVO<>(0,"批改成功",null);
        }catch (Exception e){
            resultVO=new ResultVO<>(-1,"批改失败",null);
        }
        return resultVO;
    }

    @GetMapping("/admin/detail")
    public ResultVO<String> getRecordOptions(@RequestParam("id")String id) {
        ResultVO<String> resultVO;
        try {
            String options = recordService.getStudentOptions(id);
            resultVO=new ResultVO<>(0,"获取记录成功",options);
        }catch (Exception e){
            e.printStackTrace();
            resultVO=new ResultVO<>(-1,"获取记录失败",null);
        }
        return resultVO;
    }

    @GetMapping("/list")
    public ResultVO<List<ExamRecord>> getRecords(){
        ResultVO<List<ExamRecord>> resultVO;
        try {
            List<ExamRecord> all = recordService.getAll();
            resultVO=new ResultVO<>(0,"获取记录成功",all);
        }catch (Exception e){
            e.printStackTrace();
            resultVO=new ResultVO<>(-1,"获取记录失败",null);
        }
        return resultVO;
    }

    @GetMapping("/admin/list")
    public ResultVO<RecordPageVo> getAdminRecords(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        ResultVO<RecordPageVo> resultVO;
        try {
            RecordPageVo pageVo = recordService.getAllExamRecords(pageNo, pageSize);
            resultVO=new ResultVO<>(0,"获取记录成功",pageVo);
        }catch (Exception e){
            e.printStackTrace();
            resultVO=new ResultVO<>(-1,"获取记录失败",null);
        }
        return resultVO;
    }


}
