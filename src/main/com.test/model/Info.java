package model;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/22.
 */
public class Info {
    private Long tid;
    private String type;
    private String title;
    private String description;
    private String link;
    private String img;
    private int canEnroll;   //0=no , 1=yes
    private int kind;   //0默认 1广告 2精选
    private Date createTimes;
    private Integer limitNum;
    //enroll pojo
    private Date createTime;
    //student pojo
    private String name;
    private String introduction;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getCanEnroll() {
        return canEnroll;
    }

    public void setCanEnroll(int canEnroll) {
        this.canEnroll = canEnroll;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public Date getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(Date createTimes) {
        this.createTimes = createTimes;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }
}
