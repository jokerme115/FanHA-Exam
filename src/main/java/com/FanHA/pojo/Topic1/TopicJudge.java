package com.FanHA.pojo.Topic1;

import com.FanHA.pojo.Topic;

import java.util.Arrays;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class TopicJudge extends Topic {
    private String[] options;
    private String answer;
    public TopicJudge(){

    }
    public TopicJudge( String title, int index, String data) {
        super(title, index, data);
    }

    public TopicJudge(String title, int index, String data, String[] options, String answer) {
        super(title, index, data);
        this.options = options;
        this.answer = answer;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
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
        return "TopicJudge{" +
                "options=" + Arrays.toString(options) +
                ", answer='" + answer + '\'' +
                '}';
    }
}