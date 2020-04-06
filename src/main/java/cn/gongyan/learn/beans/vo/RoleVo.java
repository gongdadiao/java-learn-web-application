/***********************************************************
 * @Description : 展示角色信息
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class RoleVo {
    @JsonProperty("id")
    private String roleName;

    @JsonProperty("name")
    private String roleDescription;

    @JsonProperty("describe")
    private String roleDetail;

    @JsonProperty("permissions")
    private List<PageVo> pageVoList;
}
