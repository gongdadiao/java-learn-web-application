package cn.gongyan.learn.service;


import cn.gongyan.learn.beans.entity.Question;
import cn.gongyan.learn.beans.entity.Resource;
import cn.gongyan.learn.beans.entity.Theme;
import cn.gongyan.learn.beans.jsonobject.OneQuestion;
import cn.gongyan.learn.beans.qo.ResourceQo;
import cn.gongyan.learn.beans.vo.ResourcePageVo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 龚研
 * @since 2020-03-19
 */
public interface ResourceService{
    JSONObject getThemes();
    Integer addResource();
    Resource getOneResource(String resId);
    Integer addOneResource(ResourceQo resourceQo,String userId);
    Integer addHomework(List<Integer> ids,String userId);
    ResourcePageVo getResourceList(Integer pageNo, Integer pageSize);
    List<Question> getHomeworkQuestions(List<Integer> ids);
}
