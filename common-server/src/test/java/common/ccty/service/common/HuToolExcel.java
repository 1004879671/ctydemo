package common.ccty.service.common;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.ccty.service.common.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuToolExcel {
    @Test
    public void test1(){

    }

    public static void main(String[] args) {
        HuToolExcel huToolExcel = new HuToolExcel();
        //下载模版
        huToolExcel.downloadTemplate(User.class);
        //导入数据
        List<User> data = huToolExcel.importData(User.class);
//        //下载数据
//        huToolExcel.downloadData(data, User.class);

    }

    public <T> void downloadTemplate(Class<T> beanType) {

        ExcelWriter writer = ExcelUtil.getWriter();

        //下载模版
        writer.setStyleSet(fontStyle(writer.getWorkbook()));
        writer.writeRow(getHeadTitleAndRule(beanType), true);

        File destFile = new File("/Users/admin/Desktop/user2.xlsx");
        writer.flush(destFile);
        System.out.println("write success!");

    }

    /**
     * 默认注解使用JsonProperty
     *
     * @param beanType
     * @return
     */
    public <T> Map<Object, Object> getHeadTitleAndRule(Class<T> beanType) {
        Field[] fields = beanType.getDeclaredFields();
        Map<Object, Object> map = new HashMap<>();
        for (Field field : fields) {
            String alisaName = field.getDeclaredAnnotation(JsonProperty.class).value();
            String ruleStr = field.getDeclaredAnnotation(JsonProperty.class).defaultValue();
            map.put(alisaName, ruleStr);
        }
        return map;
    }

    /**
     * 给规则设置样式
     *
     * @param workbook
     */
    public StyleSet fontStyle(Workbook workbook) {
        StyleSet styleSet = new StyleSet(workbook);
        Font redFont = workbook.createFont();
        redFont.setColor(Font.COLOR_RED);
        styleSet.setFont(redFont, true);

        return styleSet;
    }

    //数据导入工具类
    public <T> List<T> importData(Class<T> beanType) {
        ExcelReader excelReader = ExcelUtil.getReader("/Users/admin/Desktop/user.xlsx");

        Field[] fields = beanType.getDeclaredFields();
        for (Field field : fields) {
            String headName = field.getDeclaredAnnotation(JsonProperty.class).value();
            String fieldName = field.getName();
            excelReader.addHeaderAlias(headName, fieldName);
        }

        List<T> data = excelReader.readAll(beanType);
        for (T t : data) {
            System.out.println(t.toString());
        }
        return data;
    }


    //下载数据
    public <T> void downloadData(List<T> data, Class<T> beanType) {
        ExcelWriter excelWriter = ExcelUtil.getWriter();

        Field[] fields = beanType.getDeclaredFields();
        for (Field field : fields) {
            String headName = field.getDeclaredAnnotation(JsonProperty.class).value();
            String fieldName = field.getName();
            excelWriter.addHeaderAlias(fieldName, headName);
        }
        excelWriter.write(data);
        File destFile = new File("/Users/admin/Desktop/user2.xlsx");
        excelWriter.flush(destFile);
        System.out.println("download success！");
    }


}
