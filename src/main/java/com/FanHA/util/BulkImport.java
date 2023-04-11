package com.FanHA.util;

import com.FanHA.pojo.Topic;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

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
public class BulkImport {
    private String path;
    private String type;
    private final List<Topic> topics;

    public BulkImport(String path, String type) throws ClassNotFoundException {
        this.path = path;
        this.type = "com.FanHA.pojo.Topic." + "Topic" + type;
        topics = new ArrayList<>();
    }

    public void Bulk() {
        this.path = java.net.URLDecoder.decode(this.path, StandardCharsets.UTF_8);
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheetAt = wb.getSheetAt(0);

            int lastRowNum = sheetAt.getLastRowNum();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

            for (int i = 0; i <= lastRowNum; i++) {
                HSSFRow row = sheetAt.getRow(i);
                if (row.getCell(0) == null) continue;

                for (int j = 0; j < 5; j++) row.getCell(j).setCellType(CellType.STRING);
                row.getCell(5).setCellType(CellType.NUMERIC);

                String title = row.getCell(0).getStringCellValue();
                String date = format.format(System.currentTimeMillis());
                String[] options = new String[4];
                options[0] = row.getCell(1).getStringCellValue();
                options[1] = row.getCell(2).getStringCellValue();
                options[2] = row.getCell(3).getStringCellValue();
                options[3] = row.getCell(4).getStringCellValue();
                int answer = (int) row.getCell(5).getNumericCellValue();
                String Answer = row.getCell(answer).getStringCellValue();

                Topic topicSelect = new Topic(title, 4, format.format(System.currentTimeMillis()), options, Answer);

                topics.add(topicSelect);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        BulkImport select = new BulkImport("C:\\Users\\15351\\Desktop\\test.xls", "Select");
        select.Bulk();
        int count = 0;
        for (Topic topic : select.topics) {
            System.out.print(++count+".题目信息:");
            System.out.println(topic);
            System.out.print(count+".选项信息:");
            System.out.println(Arrays.toString(topic.getOptions()));
            System.out.print(count+".答案信息:");
            System.out.println(topic.getAnswer());
            System.out.println("--------------------");
        }

    }

}
