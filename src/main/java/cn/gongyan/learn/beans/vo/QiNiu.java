/***********************************************************
 * @Description : 返回给前端权限以便于上传文件（一个权限token只能传一个文件）
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import lombok.Data;

@Data
public class QiNiu {
    private String key;
    private String token;
}
