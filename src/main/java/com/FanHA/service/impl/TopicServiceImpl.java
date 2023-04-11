package com.FanHA.service.impl;

import com.FanHA.mapper.TopicMapper;
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
    public List<Topic> getAllTopic() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        return null;
    }

}
