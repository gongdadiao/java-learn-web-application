/***********************************************************
 * @Description : 资源表实体
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@DynamicUpdate
public class Resource {

    @Id
    private String resId;

    private String resAvatar;

    private String resCreatorId;

    private String resDescription;

    private String resName;

    private Integer resSeq;

    private Integer resThemeId;

    private String resType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDate resUploadDate;

    private String resUrl;

}
