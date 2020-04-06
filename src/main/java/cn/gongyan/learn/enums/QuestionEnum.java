/***********************************************************
 * @Description : 问题类型枚举
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.enums;

import lombok.Getter;

@Getter
public enum QuestionEnum {
    /**
     * 问题类型
     */
    DANXUAN(1, "单选题"),
    DUOXUAN(2, "多选题"),
    PANDUAN(3, "判断题"),
    TIANKONG(4,"填空题"),
    WENDA(5,"问答题");



    QuestionEnum(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    private Integer id;
    private String role;
}
