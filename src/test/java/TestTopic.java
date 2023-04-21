import com.FanHA.mapper.TopicMapper;
import com.FanHA.pojo.Items;
import com.FanHA.pojo.PageBean;
import com.FanHA.pojo.Paper;
import com.FanHA.pojo.Topic;
import com.FanHA.service.impl.TopicServiceImpl;
import com.FanHA.util.SqlSessionFactoryUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
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
import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class TestTopic {
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
                List<String> options = new ArrayList<>();

                options.add(row.getCell(1).getStringCellValue());
                options.add(row.getCell(2).getStringCellValue());
                options.add(row.getCell(3).getStringCellValue());
                options.add(row.getCell(4).getStringCellValue());

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
        List<String> options = new ArrayList<>(List.of(string));
        String answer = "The mouse on";
        Topic topic = new Topic(title, 4, format.format(System.currentTimeMillis()), options, answer);
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

            topic.setOptions(strings);
        }

        for (Topic topic : topics) {
            System.out.print(topic.getId()+".题目信息:");
            System.out.println(topic);
            System.out.print(topic.getId()+".选项信息:");
            System.out.println(topic.getOptions());
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
        int[] ids1 = {54, 55};
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
    @Test
    public void demoTest(){
        PageHelper.startPage(2, 10);
        TopicServiceImpl topicService = new TopicServiceImpl();

        List<Topic> allTopic = topicService.getAllTopic();
        for (Topic topic : allTopic)
            System.out.println(topic);
        PageBean<Topic> page= new PageBean<>(allTopic);
        String jsonString = JSON.toJSONString(page);
        System.out.println(jsonString);
    }
    @Test
    public void addTopic(){
        ArrayList<String> string = new ArrayList<>();
        string.add("2");
        string.add("3");
        string.add("4");
        string.add("5");
        Topic topic = new Topic("1", 4, string, "3");

        TopicServiceImpl topicService = new TopicServiceImpl();
        boolean b = topicService.insertTopic(topic);
        System.out.println(b);
    }
    @Test
    public void updateTopic(){
        ArrayList<String> string = new ArrayList<>();
        string.add("3");
        string.add("4");
        string.add("5");
        string.add("6");

        Topic topic = new Topic("2", 4, string, "4");
        System.out.println(topic);
        TopicServiceImpl topicService = new TopicServiceImpl();
        List<Topic> topics = topicService.selectTopicByTitle(topic.getTitle());
        topic = topics.get(0);
        System.out.println(topic);
        topicService.updateTopic(topic);
        topicService.deleteTopicOption(new int[]{topic.getId()});
        topicService.insertOption(topic);
        boolean b = topicService.updateTopic(topic);
        System.out.println(b);
    }
    /*@Test
    public void up(){
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tSWjnA4Y2v6ffUiLwcX";
        String accessKeySecret = "kyLqMiFEzTXL1nmwOvJHQ06nGjHVhz";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "fanha-exam";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "exampledir/exampleobject.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 填写字符串。
            String content = "Hello OSS，你好世界";

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 设置该属性可以返回response。如果不设置，则返回的response为空。
            putObjectRequest.setProcess("true");
            // 上传字符串。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 如果上传成功，则返回200。
            System.out.println(result.getResponse().getStatusCode());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

    }*/
    @Test
    public void selectItems(){
        TopicServiceImpl topicService = new TopicServiceImpl();
        List<Items> items = topicService.selectItems();

        System.out.println(items);
    }
}
