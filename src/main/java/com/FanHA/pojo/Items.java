package com.FanHA.pojo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class Items{
    private int id;//题目id
    private String name;//题目名字
    private String date;//创建时间
    private String type;//大题类型
    private double score;//大题分数
    private int totalNums;//小题数量
    private List<Topic> topics;//题目集

    public Items() {
    }

    public Items(int id, String name, String date, String type, double score, int totalNums) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.score = score;
        this.totalNums = totalNums;
    }

    public Items(String name, String type, double score, int totalNums, List<Topic> topics) {
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis());
        this.name = name;
        this.type = type;
        this.score = score;
        this.totalNums = totalNums;
        this.topics = topics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public void setScore(String score) {
        this.score = Double.parseDouble(score) ;
    }

    public int getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(int totalNums) {
        this.totalNums = totalNums;
    }
    public List<Topic> getTopics() {
        return topics;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", score=" + score +
                ", totalNums=" + totalNums +
                ", topics=" + topics +
                '}';
    }
}
