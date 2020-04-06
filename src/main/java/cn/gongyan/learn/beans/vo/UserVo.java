/***********************************************************
 * @Description : 展示用户信息
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserVo {
    @JsonProperty("id")
    private String userId;

    @JsonProperty("username")
    private String userUsername;

    @JsonProperty("nickname")
    private String userNickname;

    @JsonProperty("role")
    private Integer userRoleId;

    @JsonProperty("avatar")
    private String userAvatar;

    @JsonProperty("description")
    private String userDescription;

    @JsonProperty("email")
    private String userEmail;

    @JsonProperty("phone")
    private String userPhone;
}
