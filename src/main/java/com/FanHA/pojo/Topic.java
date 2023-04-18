package com.FanHA.pojo;

import java.util.Arrays;
import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class Topic {
    private int id;
    private String title;//题目
    private String type;
    private int index;//选项数目
    private  String date;//创建日期
    private String[] answer_blank;//填空题答案
    private List<String> options;//选项
    private String answer;//答案

    public Topic() {
    }

    public Topic(int id, String title, int index, String date, List<String> options) {
        this.id = id;
        this.title = title;
        this.index = index;
        this.date = date;
        this.options = options;
    }

    public Topic(String title, int index, String date, List<String> options, String answer) {
        this.title = title;
        this.index = index;
        this.date = date;
        this.options = options;
        this.answer = answer;
    }

    public Topic(String title, int index, String date) {
        this.title = title;
        this.index = index;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public String[] getAnswer_blank() {
        return answer_blank;
    }

    public void setAnswer_blank(String[] answer_blank) {
        this.answer_blank = answer_blank;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", index=" + index +
                ", data='" + date + '\'' +
                ", answer_blank=" + Arrays.toString(answer_blank) +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                '}';
    }
}
