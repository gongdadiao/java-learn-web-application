/***********************************************************
 * @Description : 处理用户提交的代码
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.controller;

import cn.gongyan.learn.beans.vo.ResultVO;
import cn.gongyan.learn.service.impl.CodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Code APIs")
@RequestMapping("/code")
public class CodeController {
    @Autowired
    CodeService codeService;

    @PostMapping("/submit")
    @ApiOperation("运行用户提交的代码并返回结果")
    ResultVO<String> submit(@RequestBody String text){
        ResultVO<String> resultVO;
        try {
            String s = codeService.execute(text);
            resultVO=new ResultVO<>(0,"运行成功",s);
        }catch (Exception e){
            resultVO=new ResultVO<>(-1,"运行失败",null);
        }
        return resultVO;
    }
}
