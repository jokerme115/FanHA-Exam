package com.FanHA.pojo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/8
 **/
public class Paper {
    int id;
    private String name;
    private String date;
    private double score;
    private int typeNums;//类型数量
    private int totalNums;//题目总数
    private int time;//答题时间
    private List<Items> items;


    public Paper() {
    }

    public Paper(int id, String name, String date, double score, int typeNums, int totalNums, int time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.score = score;
        this.typeNums = typeNums;
        this.totalNums = totalNums;
        this.time = time;
    }

    public Paper(int id, String name, String date, double score, int typeNums, int totalNums, int time, List<Items> items) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.score = score;
        this.typeNums = typeNums;
        this.totalNums = totalNums;
        this.time = time;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTypeNums() {
        return typeNums;
    }

    public void setTypeNums(int typeNums) {
        this.typeNums = typeNums;
    }

    public int getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(int totalNums) {
        this.totalNums = totalNums;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", score=" + score +
                ", typeNums=" + typeNums +
                ", totalNums=" + totalNums +
                ", time=" + time +
                ", items=" + items +
                '}';
    }
}
