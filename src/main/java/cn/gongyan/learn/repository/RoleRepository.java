/***********************************************************
 * @Description : 角色的数据库操作类
 * @author      : 龚研
 * @date        : 2020-03-22 07:35
 ***********************************************************/
package cn.gongyan.learn.repository;

import cn.gongyan.learn.beans.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
