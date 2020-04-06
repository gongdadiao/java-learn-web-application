package cn.gongyan.learn.repository;

import cn.gongyan.learn.beans.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, String> {
}
