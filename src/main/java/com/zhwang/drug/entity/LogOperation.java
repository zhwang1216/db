package com.zhwang.drug.entity;


/**
 * 操作日志实体类
 */
public class LogOperation {
    private Integer Id;        //
    private String level;        //优先级
    private String category;    //类目
    private String thread;        //进程
    private String time;        //时间
    private String location;    //位置
    private String note;        //日志信息

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "LogOperation [Id=" + Id + ", level=" + level + ", category=" + category + ", thread=" + thread
                + ", time=" + time + ", location=" + location + ", note=" + note + "]";
    }


}