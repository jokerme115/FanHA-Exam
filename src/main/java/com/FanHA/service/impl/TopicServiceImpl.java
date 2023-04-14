package com.FanHA.service.impl;

import com.FanHA.mapper.TopicMapper;
import com.FanHA.pojo.Items;
import com.FanHA.pojo.Paper;
import com.FanHA.pojo.Topic;
import com.FanHA.service.TopicService;
import com.FanHA.util.BulkImport;
import com.FanHA.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/11
 **/
public class TopicServiceImpl implements TopicService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public void insertTopic( String Path, String type) {
        try {
            BulkImport bulkImport = new BulkImport(Path, type);
            bulkImport.Bulk();
            List<Topic> topics = bulkImport.getTopics();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

            for (Topic topic : topics){
                mapper.insertTopic(topic);
                sqlSession.commit();
                int topicId = mapper.getTopicId(topic);
                topic.setId(topicId);
                mapper.insertOption(topic);
                sqlSession.commit();
            }
            sqlSession.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Topic> getTopicByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        List<Topic> topics = mapper.selectTopicByIds(ids);
        sqlSession.close();
        return topics;
    }

    @Override
    public List<Topic> getAllTopic() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        List<Topic> topics = mapper.selectTopic();
        sqlSession.close();
        return topics;
    }

    @Override
    public List<Topic> selectTopicByTitle(String title) {
        List<Topic> topics;
        title = "%".concat(title.concat("%"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        topics = mapper.selectTopicByTitle(title);
        sqlSession.close();
        return topics;
    }

    @Override
    public void deleteTopicById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        mapper.deleteTitleById(id);
        sqlSession.commit();
        mapper.deleteOptionsById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteTopicByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        mapper.deleteTitleByIds(ids);
        sqlSession.commit();
        mapper.deleteOptionsByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int selectItemsByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        int id = mapper.selectItemsByName(name);
        sqlSession.close();
        return id;
    }

    @Override
    public int[] selectTopicByItems(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        int[] ids = mapper.selectTopicByItems(id);
        sqlSession.close();
        return ids;
    }

    @Override
    public void insertItems(String name, String type, double score, int totalNums, int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);
        String s = String.valueOf(score);

        mapper.insertItem(name, type, s, totalNums);
        sqlSession.commit();

        int id = mapper.selectItemsByName(name);
        mapper.insertTopicToItem(id, ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Paper createPaper(String name, double score, int numsType, int numsTotal, int time, Items<?>[] items) {
        return new Paper(name, score, numsType, numsTotal, time, items);
    }

    @Override
    public Items<?> createItem(String name, String type, double score, int totalNums, int[] ids) {
        List<Topic> topicByIds = getTopicByIds(ids);

        return new Items<Topic>(name, type, score, totalNums, topicByIds);
    }
}
