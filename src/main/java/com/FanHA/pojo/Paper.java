package com.FanHA.pojo;

import java.util.Arrays;

/**
 * @author HeTao
 * @data 2023/4/8
 **/
public class Paper {
    private String name;
    private int score;
    private int numsType;//类型数量
    private int numsTotal;//题目总数
    private int time;//答题时间
    private Items<?>[] items;


    public Paper(String name, int score, int numsType, int numsTotal, int time, Items<?>[] items) {
        System.out.println(2);
        this.name = name;
        this.score = score;
        this.numsType = numsType;
        this.numsTotal = numsTotal;
        this.time = time;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumsType() {
        return numsType;
    }

    public void setNumsType(int numsType) {
        this.numsType = numsType;
    }

    public int getNumsTotal() {
        return numsTotal;
    }

    public void setNumsTotal(int numsTotal) {
        this.numsTotal = numsTotal;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Items<?>[] getItems() {
        return items;
    }

    public void setItems(Items<?>[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", numsType=" + numsType +
                ", numsTotal=" + numsTotal +
                ", time=" + time +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
