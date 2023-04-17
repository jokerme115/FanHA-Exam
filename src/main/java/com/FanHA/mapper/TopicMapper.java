package com.FanHA.mapper;

import com.FanHA.pojo.Topic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 查找所有题目
     * @return 将Topic封装成List返回
     */
    List<Topic> selectTopic();
    /**
     * 通过id查询题目的选项
     * @param id 题目id
     * @return 符合id的所有选项
     */
    List<String> selectOption(int id);

    /**
     * 通过模糊查询题目
     * @param title 模糊查询字段
     * @return 返回题目集合
     */
    List<Topic> selectTopicByTitle(String title);

    List<Topic> selectTopicByIds(@Param("ids") int[] ids);

    /**
     * 通过id删除题目
     * @param id 题目序号
     */
    @Delete("DELETE from topic where id = #{id}")
    void deleteTitleById(int id);

    /**
     * 通过id删除选项
     * @param id 题目序号
     */
    @Delete("DELETE from topic_option where id = #{id}")
    void deleteOptionsById(int id);

    /**
     * 通过一组id删除题目
     * @param ids 题目序号数组
     */
    void deleteTitleByIds(@Param("ids") int[] ids);

    /**
     * 通过一组id删除选项
     * @param ids 题目序号数组
     */
    void deleteOptionsByIds(@Param("ids") int[] ids);

    /**
     * 添加一组题目集
     * @param name 题集名称
     * @param type 题集类型
     * @param score 题集分数
     * @param totalNums 题集数量
     */
    void insertItem(@Param("name") String name,@Param("type") String type,@Param("score") String score,@Param("totalNums") int totalNums);

    /**
     * 向题目_题目集进行关联
     * @param id 题目集id
     * @param ids 题目的id集
     */
    void insertTopicToItem(@Param("id") int id, @Param("ids") int[] ids);

    /**
     * 通过题目集名称查询题目集Id
     * @param name 题目集名称
     * @return 题目集Id
     */
    @Select("SELECT id FROM items WHERE name = #{name}")
    int selectItemsByName(String name);

    /**
     * 通过题目集id查询题目id
     * @param id 题目集id
     * @return 封装题目id为ids数组
     */
    @Select("select topicId FROM item_topic WHERE itemId = #{id}")
    int[] selectTopicByItems(int id);
}
