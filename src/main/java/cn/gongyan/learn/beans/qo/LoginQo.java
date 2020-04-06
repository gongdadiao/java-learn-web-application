/***********************************************************
 * @Description : 用户发送到服务器的登陆查询信息
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginQo {
    /**
     * 1表示 老师，2表示 学生 3表示管理员
     */
    private Integer loginType;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
}
