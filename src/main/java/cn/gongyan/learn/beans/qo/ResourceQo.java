/***********************************************************
 * @Description : 老师发送到服务器的资源添加信息
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResourceQo {

    private String resAvatar;
    private String resDescription;
    private String resName;
    private Integer resSeq;
    private Integer resThemeId;
    private String resType;
    private String resUrl;
}
