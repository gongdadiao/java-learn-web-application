/***********************************************************
 * @Description : 用户角色枚举
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    /**
     * 用户角色，和数据库里面的role表相对应
     */
    ADMIN(1, "管理员"),
    TEACHER(2, "教师"),
    STUDENT(3, "学生");

    RoleEnum(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    private Integer id;
    private String role;
}
