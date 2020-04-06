/***********************************************************
 * @Description : 七牛云上传工具
 * @author      : 龚研
 * @date        : 2019-05-17 07:43
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.log4j.Logger;

import java.io.File;

public class UpLoader {
    private static final Logger logger = Logger.getLogger(UpLoader.class);



    //设置好账号的ACCESS_KEY和SECRET_KEY
    static final String ACCESS_KEY = "3bc39xTGqj3Le3KxFIRKUoiHcZ0nR8P69fRy8GvU";
    static final String SECRET_KEY = "Yt8pa13fKxrsDs43Qo2jmgljs1X-R9-U_AWgp7Zk";
    //要上传的空间
    static final String BUCKET_NAME = CommonValues.bucketName;


    public static String uploadQiniuText(String type,String text,String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg;
        cfg = new Configuration(Zone.zone1());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = ACCESS_KEY;
        String secretKey = SECRET_KEY;
        String bucket = BUCKET_NAME;
        long millis = System.currentTimeMillis();
        String key = type+ "/"+fileName+"?tId="+millis;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        String result=null;
        try {
            Response response = uploadManager.put(text.getBytes(), key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            logger.info("{七牛图片上传key: "+ putRet.key+",七牛图片上传hash: "+ putRet.hash+"}");

            result = "http://q852a0qij.bkt.clouddn.com/"+type+"/"+fileName+"%3FtId%3D"+millis;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

        return result;
    }

    public String uploadQiniu (File localFilePath,String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg;
        cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = ACCESS_KEY;
        String secretKey = SECRET_KEY;
        String bucket = BUCKET_NAME;
        //如果是Windows情况下，格式是 D:\23912475_130759767000_2.jpg
//        String localFilePath = "D:\\23912475_130759767000_2.jpg";
        //        String localFilePath = "/home/qiniu/test.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "images/"+fileName+"?tId="+System.currentTimeMillis();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        String result = null;

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            logger.info("{七牛图片上传key: "+ putRet.key+",七牛图片上传hash: "+ putRet.hash+"}");

            result = "外链域名(如:image.domain.com)"+putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            result = null;
        }
        return result;
    }
}
