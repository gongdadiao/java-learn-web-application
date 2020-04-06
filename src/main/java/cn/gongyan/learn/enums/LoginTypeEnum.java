/***********************************************************
 * @Description : 账号类型枚举
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.enums;

import lombok.Getter;

@Getter
public enum LoginTypeEnum {
    /**
     * 用户的账号类型，1代表  老师  ，2代表  学生  ，3代表  管理员
     */
    TEACHER(1, "老师"),
    STUDENT(2, "学生"),
    ADMIN(3,"管理员");

    LoginTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    private Integer type;
    private String name;
}
