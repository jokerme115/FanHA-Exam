package com.FanHA.service;

import com.FanHA.pojo.Items;
import com.FanHA.pojo.Paper;
import com.FanHA.pojo.Topic;

import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/11
 **/
public interface TopicService {
    Paper createPaper(String name, double score, int numsType, int numsTotal, int time, Items<?>[] items);
    Items<?> createItem(String name, String type, double score, int totalNums, int[] ids);
    void insertTopic(String Path, String type);
    List<Topic> getTopicByIds(int[] ids);
    List<Topic> getAllTopic();
    List<Topic> selectTopicByTitle(String title);
    void deleteTopicById(int id);

    /**
     * 通过一组id值删除题目
     * @param ids
     */
    void deleteTopicByIds(int[] ids);
    int selectItemsByName(String name);
    int[] selectTopicByItems(int id) ;
    void insertItems(String name, String type, double score, int totalNums, int[] ids);
}
