/***********************************************************
 * @Description : 教学章节表
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.entity;

import java.util.List;

public class Theme {


    private Integer themeId;

    private String themeName;

    private Integer themeSeq;

    private String themeDescription;

    private List<Resource> resourceList;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public Integer getThemeSeq() {
        return themeSeq;
    }

    public void setThemeSeq(Integer themeSeq) {
        this.themeSeq = themeSeq;
    }

    public String getThemeDescription() {
        return themeDescription;
    }

    public void setThemeDescription(String themeDescription) {
        this.themeDescription = themeDescription;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "themeId=" + themeId +
                ", themeName='" + themeName + '\'' +
                ", themeSeq=" + themeSeq +
                ", themeDescription='" + themeDescription + '\'' +
                ", resourceList=" + resourceList +
                '}';
    }
}
