package com.FanHA.servlet;

import com.FanHA.pojo.Items;
import com.FanHA.pojo.PageBean;
import com.FanHA.pojo.Paper;
import com.FanHA.pojo.Topic;
import com.FanHA.service.impl.TopicServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author HeTao
 * @data 2023/4/13
 **/
@WebServlet("/Topic/*")
public class TopicServlet extends BaseServlet{
    TopicServiceImpl topicService = new TopicServiceImpl();

    /**
     * 新建试卷
     * @param req 通过post方法获取试卷的名字，大题数量，题目数量，总分，时间，题目集序号
     * @param resp 返回"success"代表成功
     */
    public void addTopic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        List<String> options = new ArrayList<>();
        String title;
        String answer;

        /*处理数据*/

        JSONObject jsonObject = JSON.parseObject(s);
        title = jsonObject.getString("title");
        answer = jsonObject.getString("answer");
        JSONArray options2 = JSON.parseArray(jsonObject.getString("options"));
        for (Object d : options2){
            String value = JSON.parseObject(d.toString()).getString("value");
            options.add(value);
        }

        Topic topic = new Topic(title, 4 , options, answer);

        boolean b = topicService.insertTopic(topic);
        if (b){
            resp.getWriter().write("success");
        }else resp.getWriter().write("false");
    }
    public void showPaper(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        String readLine = reader.readLine();

        //查询数据
        Paper paper = topicService.selectPaper(readLine);

        if (paper != null) {
            //转成json
            String jsonString = JSON.toJSONString(paper);
            System.out.println(jsonString);
            //更改编码 传输
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }else resp.getWriter().write("false");
    }
    public void getTopic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String currentPage = req.getParameter("currentPage");
        String total = req.getParameter("pageSize");

        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(total));
        List<Topic> allTopic = topicService.getAllTopic();
        PageBean<Topic> page= new PageBean<>(allTopic);

        if (allTopic != null){
            //转成json

            String jsonString = JSON.toJSONString(page);
            //更改编码 传输
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }else resp.getWriter().write("false");
    }
    public void getAllTopic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Topic> allTopic = topicService.getAllTopic();

        if (allTopic != null){
            String jsonString = JSON.toJSONString(allTopic);

            //更改编码 传输
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }else resp.getWriter().write("false");
    }
    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        int[] ints = JSON.parseObject(s, int[].class);

        if (ints != null){
            topicService.deleteTopicByIds(ints);
            resp.getWriter().write("success");
        }else resp.getWriter().write("false");
    }
    public void updateTopic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        List<String> options = new ArrayList<>();
        String title;
        String answer;

        /*处理数据*/

        JSONObject jsonObject = JSON.parseObject(s);
        if (jsonObject != null){
            String id = jsonObject.getString("id");
            title = jsonObject.getString("title");
            String[] strings = JSON.parseObject(jsonObject.getString("options"), String[].class);
            answer = jsonObject.getString("answer");
            options.addAll(Arrays.asList(strings));


            Topic topic = new Topic(Integer.parseInt(id), title, 4 , options, answer);
            topicService.updateTopic(topic);
            topicService.deleteTopicOption(new int[]{topic.getId()});
            topicService.insertOption(topic);
            resp.getWriter().write("success");
        }
    }
    public void getItems(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String currentPage = req.getParameter("currentPage");
        String total = req.getParameter("pageSize");

        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(total));

        List<Items> items = topicService.selectItems();
        PageBean<Items> page= new PageBean<>(items);

        if (items != null){
            //转成json
            String jsonString = JSON.toJSONString(page);
            System.out.println(jsonString);
            //更改编码 传输
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }else resp.getWriter().write("false");
    }
}
