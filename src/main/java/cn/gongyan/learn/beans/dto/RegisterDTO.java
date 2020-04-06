/***********************************************************
 * @Description : 注册接口参数
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String userRegName;
    private String password;
    private String password2;
    /**
     * 邀请码
     */
    private String invitedCode;
}
