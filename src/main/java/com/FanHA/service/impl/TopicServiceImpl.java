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
import org.apache.poi.ss.formula.functions.T;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
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
    public int[] selectTopicByItems(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        int[] ids = mapper.selectTopicByItems(id);
        sqlSession.close();
        return ids;
    }

    @Override
    public List<Topic> selectTopicByItems(int[] ids) {
        return null;
    }


    @Override
    public void insertItems(String name, String type, double score, int totalNums, int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);
        String s = String.valueOf(score);

        mapper.insertItem(name, type, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis()) , s, totalNums);
        sqlSession.commit();

        int id = mapper.selectItemsByName(name);
        mapper.insertTopicToItem(id, ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void insertPaper(String name, int time, int[] ids) {
        int typeNums = 0;
        int numsTotal = 0;
        double score = 0;
        HashSet<String> hashSet = new HashSet<>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis());

        //得到每一个items的对象进行初始化数据
        for (int id : ids){
            Items items = mapper.selectItemsById(id);
            numsTotal += items.getTotalNums();
            score += items.getScore();
            hashSet.add(items.getType());
        }
        typeNums = hashSet.size();

        String s = String.valueOf(score);
        mapper.insertPaper(name, date, s, typeNums, numsTotal, time);
        sqlSession.commit();
        int id = mapper.selectPaperByName(name);
        mapper.insertItemToPaper(id, ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Paper selectPaper(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        Paper paper = mapper.selectPaperByNameToPaper(name);//获取数据库中paper内容还差items
        List<Items> items = new ArrayList<>();

        int id = paper.getId();
        int[] ids = mapper.selectAllItemsFromPaper(id);

        for (int j : ids) {
            Items item = mapper.selectItemsById(j);
            int[] ints = selectTopicByItems(item.getId());
            List<Topic> topicByIds = getTopicByIds(ints);
            item.setTopics(topicByIds);
            items.add(item);
        }

        paper.setItems(items);
        sqlSession.close();
        return paper;
    }
}
