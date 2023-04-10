package com.FanHA.pojo.Topic;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class Topic {
    private int id;
    private String title;//题目
    private int index;//选项数目
    private final String data;//创建日期

    public Topic(String title, int index, String data) {
        this.title = title;
        this.index = index;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", index=" + index +
                ", data='" + data + '\''+
                '}';
    }
}
