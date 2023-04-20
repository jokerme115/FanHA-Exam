package com.FanHA.pojo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author HeTao
 * @data 2023/4/20
 **/
public class PageBean<T> {
    PageInfo<T> pageInfo;
    List<T> list;
    public PageBean( List<T> list) {
        this.pageInfo = new PageInfo<>(list);
        this.list = list;
    }

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageInfo=" + pageInfo +
                ", list=" + list +
                '}';
    }
}
