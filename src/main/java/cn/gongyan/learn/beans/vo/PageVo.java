/***********************************************************
 * @Description : action前端展示类
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class PageVo {
    @JsonProperty("actionEntitySet")
    private List<ActionVo> actionVoList;

    @JsonProperty("permissionId")
    private String pageName;

    @JsonProperty("permissionName")
    private String pageDescription;
}
