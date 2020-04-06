/***********************************************************
 * @Description : 信息下载工具
 * @author      : 龚研
 * @date        : 2019-05-17 07:43
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownLoader {

    public static String download(String address)  {

        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String s="";
        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();

            byte[] cl = new byte[1024];
            byte[] buffer = cl.clone();
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                String tmp=new String(buffer);
                s+=tmp;
                buffer=cl.clone();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        String result = s.trim();
        System.out.println(result.length());
        return result;
    }
}
