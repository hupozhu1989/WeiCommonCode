package com.wei.common.resttemplate;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/4
 */
public class FriendlyLink {
    private Integer id;
    private String publishTime;
    private String companyName;
    private String companyUrl;

    public FriendlyLink(Integer id, String publishTime, String companyName, String companyUrl) {
        this.id = id;
        this.publishTime = publishTime;
        this.companyName = companyName;
        this.companyUrl = companyUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    @Override
    public String toString() {
        return "FriendlyLink{" +
                "id=" + id +
                ", publishTime='" + publishTime + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                '}';
    }
}
