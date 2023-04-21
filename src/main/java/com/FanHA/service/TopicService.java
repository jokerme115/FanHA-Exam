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
    /*查询*/
    /**
     * 通过topic查询选项
     * @param topic 为封装的Topic
     * @return 返回String的集合
     */
    List<String> selectOptionsByTopic(Topic topic);
    /**
     * 通过id值查询题目
     * @param ids id数组
     * @return 返回topic集合
     */
    List<Topic> getTopicByIds(int[] ids);
    /**
     * 获得所有的topic
     * @return 返回topic集合
     */
    List<Topic> getAllTopic();
    /**
     * 通过题目查询topic
     * @param title 题目
     * @return 返回topic集合
     */
    List<Topic> selectTopicByTitle(String title);
    /**
     * 通过题目集id查询题目id
     * @param id 题目集id
     * @return 题目id数组
     */
    int[] selectTopicByItems(int id) ;
    List<Topic> selectTopicByItems(int[] ids) ;
    /**
     * 查找试卷
     * @param name 试卷名字
     * @return 试卷Paper
     */
    Paper selectPaper(String name);
    /*增加*/
    /**
     * 批量插入Topic
     * @param Path excel文件地址
     * @param type 题目类型
     */
    void insertTopic(String Path, String type);
    /**
     * 插入一个题目集
     * @param name 题目集的名字
     * @param type 题目集的类型
     * @param score 题目集的分数
     * @param totalNums 题目的总数
     * @param ids 题目的id
     */
    void insertItems(String name, String type, double score, int totalNums, int[] ids);
    /**
     * 添加试卷
     * @param name 试卷名字
     * @param time 答题时间
     * @param ids 题目集的id
     */
    void insertPaper(String name, int time, int[] ids);
    /*删除*/
    /**
     * 通过id删除题目
     * @param id 题目id
     */
    void deleteTopicById(int id);
    /**
     * 通过一组id值删除题目
     * @param ids 一组id
     */
    void deleteTopicByIds(int[] ids);
    void deleteTopicOption(int[] ids);
    /*更改*/
    /*判断是否存在通过*/
    /**
     * 通过试题集名字判断是否存在
     * @param name 试题集名
     * @return 是否存在
     */
    boolean judgeItems(String name);
    /**
     * 通过试卷名字判断是否存在
     * @param name 试卷名
     * @return 是否存在
     */
    boolean judgePaper(String name);
    boolean judgeTopic(Topic topic);
    boolean insertTopic(Topic topic);
    void insertOption(Topic topic);
    boolean updateTopic(Topic topic);
    List<Items> selectItems();
}
