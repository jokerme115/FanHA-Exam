package com.FanHA.servlet;

import com.FanHA.pojo.Items;
import com.FanHA.pojo.PageBean;
import com.FanHA.pojo.Paper;
import com.FanHA.pojo.Topic;
import com.FanHA.service.impl.TopicServiceImpl;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

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
        System.out.println(s);

        Topic topic = JSON.parseObject(s, Topic.class);
        System.out.println(topic);

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
        System.out.println(allTopic);
        PageBean<Topic> page= new PageBean<>(allTopic);

        if (allTopic != null){
            //转成json

            String jsonString = JSON.toJSONString(page);
            System.out.println(jsonString);
            //更改编码 传输
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }else resp.getWriter().write("false");
    }
}
