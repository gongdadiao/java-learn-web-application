/***********************************************************
 * @Description : 展示资源信息（给教师）
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ResourceVo {
    @JsonProperty("id")
    private String resId;
    @JsonProperty("name")
    private String resName;
    @JsonProperty("avatar")
    private String resAvatar;
    @JsonProperty("desc")
    private String resDescription;

    @JsonProperty("url")
    private String resUrl;


    @JsonProperty("creator")
    private String resCreator;

    /**
     * 考试限制的时间，单位为分钟
     */
    @JsonProperty("theme")
    private Integer resThemeId;

    @JsonProperty("type")
    private String resType;

    /**
     * 更新时间
     */
    @JsonProperty("updateTime")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDate resUploadDate;


}
