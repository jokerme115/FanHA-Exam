package com.FanHA.util;

import com.FanHA.pojo.Items;

import java.util.List;


/**
 * @author HeTao
 * @data 2023/4/10
 **/
public class BulkImport {
    private String path;
    private String type;
    private Items items;
    private List topics;

    public BulkImport(String path, String type) throws ClassNotFoundException {
        this.path = path;

        this.type = "com.FanHA.pojo.Topic."+"Topic"+type;
        Class<?> aClass = Class.forName(this.type);
        
    }

    public static void main(String[] args) throws ClassNotFoundException {
       BulkImport bulkImport = new BulkImport("aaa", "Select");

    }

}
