package com.FanHA.mapper;

import com.FanHA.pojo.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/11
 **/
public interface TopicMapper {
    /**
     * 添加题目、题目数量、时间、答案
     * @param topic 传入还未查询id的题目集
     */
    @Insert("insert into topic values (null, #{title}, #{index}, #{date}, #{answer})")
    void insertTopic(Topic topic);

    /**
     *
     * @param topic 传入查询id的Topic
     * @return 返回id
     */
    @Select("select id from topic where title = #{title} and date = #{date} and answer = #{answer}")
    int getTopicId(Topic topic);

    /**
     * 把id和答案传入到数据库
     * @param topic 传入带有id的Topic
     */
    @Select("select * from fanha.topic_option where id = #{id}")
    void insertOption(Topic topic);

    List<Topic> selectTopic();

    List<String> selectOption(int id);
}
