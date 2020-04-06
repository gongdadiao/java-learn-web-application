package cn.gongyan.learn.mapper;

import cn.gongyan.learn.beans.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
    Resource findOneResouce(String id);
}
