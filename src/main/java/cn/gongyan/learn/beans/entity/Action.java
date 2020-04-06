/***********************************************************
 * @Description : 前端操作
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Action {
    @Id
    @GeneratedValue
    private Integer actionId;

    private String actionName;

    private String actionDescription;

    private Boolean defaultCheck;
}
