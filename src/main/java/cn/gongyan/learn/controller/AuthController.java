/***********************************************************
 * @Description : 给予用户其请求的权限
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.controller;

import cn.gongyan.learn.beans.vo.QiNiu;
import cn.gongyan.learn.utils.CommonValues;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Api(tags = "Auth APIs")
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/token/{type}")
    @ApiOperation("获取上传许可")
    public QiNiu getToken(@PathVariable String type) {
        QiNiu qiNiu = new QiNiu();
        // 这三个 就是  ak  sk   和你的 空间名
        String accessKey = "3bc39xTGqj3Le3KxFIRKUoiHcZ0nR8P69fRy8GvU";
        String secretKey = "Yt8pa13fKxrsDs43Qo2jmgljs1X-R9-U_AWgp7Zk";
        String bucket = CommonValues.bucketName;
        long expireSeconds = 600;
        StringMap putPolicy = new StringMap();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket,null, expireSeconds,putPolicy);
        qiNiu.setKey(UUID.randomUUID().toString()+"."+type);
        qiNiu.setToken(upToken);
        return qiNiu;
    };
}
