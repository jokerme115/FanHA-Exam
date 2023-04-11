package com.FanHA.pojo.Topic;

import com.FanHA.pojo.Topic;

import java.util.Arrays;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class TopicBlank extends Topic {
    private String[] answer;

    public TopicBlank(String title, int index, String data) {
        super(title, index, data);
    }

    public TopicBlank(String title, int index, String data, String[] answer) {
        super(title, index, data);
        this.answer = answer;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "TopicBlank{" +
                "answer=" + Arrays.toString(answer) +
                '}';
    }
}
