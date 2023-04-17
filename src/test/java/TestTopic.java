import com.FanHA.mapper.TopicMapper;
import com.FanHA.pojo.Items;
import com.FanHA.pojo.Paper;
import com.FanHA.pojo.Topic;
import com.FanHA.service.impl.TopicServiceImpl;
import com.FanHA.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class TestTopic {
    @Test
    public void testPaper(){
        Paper paper;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Topic topicSelect1 = new Topic("“举世闻名”中“举”的意思是()，“举重”中“举”的意思是()。", 4,
                 format.format(new Date(System.currentTimeMillis())), new String[]{"往上托", "全", "推动", "举动"}, "往上拖");
        Topic topicSelect2 = new Topic("A: hover said what kind of state in the following text hyperlinks?", 4,
                format.format(new Date(System.currentTimeMillis())), new String[]{"The mouse click", "The mouse without", "The mouse on", "After the visit"}, "The mouse on");
        List<Topic> list = new ArrayList<>();
        list.add(topicSelect1);
        list.add(topicSelect2);
        List<Items> items1 = new ArrayList<>();

        Items items = new Items("item1","select", 10, 2, list);
        items1.add(items);
        paper = new Paper(1,"测试用例", "0" ,10, 1, 2, 10, items1);
        System.out.println(paper);
        System.out.println(items);
        System.out.println(topicSelect1);
        System.out.println(topicSelect2);
    }
    @Test
    public void testImport(){
        List<Topic> topicSelects = new ArrayList<>();

        try {
            String path = "F:\\test.xls";
            path = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8);
            FileInputStream inputStream = new FileInputStream(path);
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet HSSfSheet = wb.getSheetAt(0);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

            for (int i = 0; i <= 11; i++){
                //获取行
                HSSFRow row = HSSfSheet.getRow(i);
                //获取数据
                if (row.getCell(0) == null) continue;

                //设置表格类型
                for (int j = 0; j < 5; j++)
                    row.getCell(i).setCellType(CellType.STRING);
                String title = row.getCell(0).getStringCellValue();
                String date = format.format(System.currentTimeMillis());
                String[] options = new String[4];
                options[0] = row.getCell(1).getStringCellValue();
                options[1] = row.getCell(2).getStringCellValue();
                options[2] = row.getCell(3).getStringCellValue();
                options[3] = row.getCell(4).getStringCellValue();

                int answer = (int) row.getCell(5).getNumericCellValue();
                String Answer = row.getCell(answer).getStringCellValue();
                Topic topicSelect =  new Topic(title, 4 , date, options, Answer);
                topicSelects.add(topicSelect);
                Topic[] array = topicSelects.toArray(new Topic[0]);

                for (Topic e : array)
                    System.out.println(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void function2(){
        String title = "A: hover said what kind of state in the following text hyperlinks?";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String[] string = new String[]{"The mouse click","The mouse without", "The mouse on", "After the visit"};
        String answer = "The mouse on";
        Topic topic = new Topic(title, 4, format.format(System.currentTimeMillis()), string, answer);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);
        mapper.insertTopic(topic);
        sqlSession.commit();
        int topicId = mapper.getTopicId(topic);
        topic.setId(topicId);
        mapper.insertOption(topic);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void TestTopicService(){
        String path = "C:\\Users\\15351\\Desktop\\test.xls";
        String type = "select";
        TopicServiceImpl topicServiceImpl = new TopicServiceImpl();
        topicServiceImpl.insertTopic(path, type);
    }
    @Test
    public void TestGetTopic(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);
        List<Topic> topics = mapper.selectTopic();
        for (Topic topic : topics) {
            List<String> strings = mapper.selectOption(topic.getId());
            String[] array = strings.toArray(new String[0]);
            topic.setOptions(array);
        }

        for (Topic topic : topics) {
            System.out.print(topic.getId()+".题目信息:");
            System.out.println(topic);
            System.out.print(topic.getId()+".选项信息:");
            System.out.println(Arrays.toString(topic.getOptions()));
            System.out.print(topic.getId()+".答案信息:");
            System.out.println(topic.getAnswer());
            System.out.println("--------------------");
        }
    }
    @Test
    public void TestSelectTopicByTitle(){
        TopicServiceImpl topicService = new TopicServiceImpl();
        List<Topic> topics = topicService.selectTopicByTitle("A");
        System.out.println(topics);

    }
    @Test
    public void TestDeleteTopicById(){
        int id = 25;
        TopicServiceImpl topicService = new TopicServiceImpl();
        topicService.deleteTopicById(id);
    }
    @Test
    public void deleteTitleByIds(){
        int[] ids1 = {26, 27};
        TopicServiceImpl topicService = new TopicServiceImpl();
        topicService.deleteTopicByIds(ids1);
    }
    @Test
    public void getTopicByIds(){
        int[] ids = {28,29};
        TopicServiceImpl topicService = new TopicServiceImpl();
        List<Topic> topicByIds = topicService.getTopicByIds(ids);

        for (Topic topic : topicByIds)
            System.out.println(topic);
    }
    @Test
    public void createItem(){
        int[] ids = {29,30,31,32,33,34};
        String name = "testItems2";
        String type = "select";
        int totalNums = ids.length;
        double score = 65;

        TopicServiceImpl topicService = new TopicServiceImpl();
        topicService.insertItems(name, type, score, totalNums, ids);
    }
    @Test
    public void testSelectItems(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);
        Items items = mapper.selectItemsById(7);
        System.out.println(items);

    }
    @Test
    public void createPaper(){
        int[] ids = {6, 7};
        String name = "testPaper";
        int time = 60;

        TopicServiceImpl topicService = new TopicServiceImpl();
        topicService.insertPaper(name, time, ids);

    }
    @Test
    public void selectPaper(){
        TopicServiceImpl topicService = new TopicServiceImpl();
        Paper paper = topicService.selectPaper("testPaper");
        //没有选项！！！
        System.out.println(paper);
    }
    @Test
    public void selectAllItemsFromPaper(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TopicMapper mapper = sqlSession.getMapper(TopicMapper.class);

        int[] ints = mapper.selectAllItemsFromPaper(1);
        System.out.println(Arrays.toString(ints));
    }
}
