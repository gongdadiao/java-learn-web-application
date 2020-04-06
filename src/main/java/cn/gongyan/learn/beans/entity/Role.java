/***********************************************************
 * @Description : 用户角色表（目前只有学生和老师）
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
public class Role {
    @Id
    @GeneratedValue
    private Integer roleId;
    private String roleName;
    private String roleDescription;
    private String roleDetail;
    /**
     * 角色所能访问的页面的主键集合(用-连接起来字符串)
     */
    private String rolePageIds;
}
