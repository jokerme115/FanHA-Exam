package com.FanHA.pojo.Topic;

import com.FanHA.pojo.Topic;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class TopicSelect extends Topic {
    private String[] options;//选项
    private String answer;//答案

    public TopicSelect(String title, int index, String data) {
        super(title, index, data);
    }

    public TopicSelect(String title, int index, String data, String[] options, String answer) {
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
}
