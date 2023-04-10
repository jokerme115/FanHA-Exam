import com.FanHA.pojo.Items;
import com.FanHA.pojo.Paper;
import com.FanHA.pojo.Topic.TopicSelect;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
        TopicSelect topicSelect1 = new TopicSelect("“举世闻名”中“举”的意思是()，“举重”中“举”的意思是()。", 4,
                 format.format(new Date(System.currentTimeMillis())), new String[]{"往上托", "全", "推动", "举动"}, "往上拖");
        TopicSelect topicSelect2 = new TopicSelect("A: hover said what kind of state in the following text hyperlinks?", 4,
                format.format(new Date(System.currentTimeMillis())), new String[]{"The mouse click", "The mouse without", "The mouse on", "After the visit"}, "The mouse on");
        Items<TopicSelect> items = new Items<>("select", 10, 2, new TopicSelect[]{topicSelect1, topicSelect2});
        paper = new Paper("测试用例", 10, 1, 2, 10, new Items[]{items});
        System.out.println(paper);
        System.out.println(items);
        System.out.println(topicSelect1);
        System.out.println(topicSelect2);
    }
    @Test
    public void testImport(){
        String rootPath = "C:\\Users\15351\\Desktop";
        String importOrderDTO = "";
        Items<TopicSelect> items;


        final String SEPA = File.separator;
        List<TopicSelect> topicSelects = new ArrayList<>();

        try {
            String path = "F:\\test.xls";
            path = java.net.URLDecoder.decode(path, "utf-8");
            FileInputStream inputStream = new FileInputStream(path);
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet HSSfSheet = wb.getSheetAt(0);
            int lastRowNum = HSSfSheet.getLastRowNum();
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");

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
                TopicSelect topicSelect =  new TopicSelect(title, 4 , date, options, Answer);
                topicSelects.add(topicSelect);
                TopicSelect[] array = topicSelects.toArray(new TopicSelect[0]);

                for (TopicSelect e : array)
                    System.out.println(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
