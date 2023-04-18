package com.FanHA.pojo.Topic1;

import com.FanHA.pojo.Topic;

import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class TopicSelect extends Topic {
    private List<String> options;//选项
    private String answer;//答案
    public TopicSelect(){

    }
    public TopicSelect(String title, int index, String data) {
        super(title, index, data);
    }

    public TopicSelect(String title, int index, String data, List<String> options, String answer) {
        super(title, index, data);
        this.options = options;
        this.answer = answer;
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
    public void print(String string){
        System.out.println(string);
    }
}
