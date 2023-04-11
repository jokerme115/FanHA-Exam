package com.FanHA.service;

import com.FanHA.pojo.Topic;

import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/11
 **/
public interface TopicService {
    void insertTopic(String Path, String type);
    List<Topic> getAllTopic();
}
