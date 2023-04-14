package com.FanHA.servlet;

import com.FanHA.service.impl.TopicServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HeTao
 * @data 2023/4/13
 **/
@WebServlet("Topic/*")
public class TopicServlet extends BaseServlet{
    TopicServiceImpl topicService = new TopicServiceImpl();

    /**
     * 新建试卷
     * @param req 通过post方法获取试卷的名字，大题数量，题目数量，总分，时间，题目集序号
     * @param resp 返回"success"代表成功
     */
    public void addPaper(HttpServletRequest req, HttpServletResponse resp){

    }
}
