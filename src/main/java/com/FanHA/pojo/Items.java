package com.FanHA.pojo;

import java.util.Arrays;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class Items <T>{
    private String type;//大题类型
    private double score;//大题分数
    private int totalNums;//小题数量
    private T[] topics;//题目集

    public Items(String type, double score, int totalNums, T[] topics) {
        this.type = type;
        this.score = score;
        this.totalNums = totalNums;
        this.topics = topics;
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

    public int getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(int totalNums) {
        this.totalNums = totalNums;
    }

    public T[] getTopics() {
        return topics;
    }

    public void setTopics(T[] topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "Items{" +
                ", type='" + type + '\'' +
                ", score=" + score +
                ", totalNums=" + totalNums +
                ", topics=" + Arrays.toString(topics) +
                '}';
    }
}
