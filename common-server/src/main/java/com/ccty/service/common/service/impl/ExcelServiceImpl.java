package com.ccty.service.common.service.impl;


import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccty.service.common.dao.ExcelMapper;
import com.ccty.service.common.entity.Excel;
import com.ccty.service.common.entity.dto.ExcelDto;
import com.ccty.service.common.service.ExcelService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper,Excel> implements ExcelService {

    private static Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);


    @Autowired
    private ExcelMapper excelMapper;

    public int addList(List<Excel> list) {
        return excelMapper.insertList(list);
    }

    @Override
    public List<Excel> qryExcelList(ExcelDto excelDto ) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name",excelDto.getName());

//        List<ExcelVo> excelVosList = excelMapper.selectList(queryWrapper);
        List<Excel> excelList = excelMapper.selectList(queryWrapper);

        return excelList;
    }




    public <T> void downloadTemplate(Class<T> beanType,String fileName) {

        ExcelWriter writer = ExcelUtil.getWriter();

        //????????????
        writer.setStyleSet(fontStyle(writer.getWorkbook()));
        writer.writeRow(getHeadTitleAndRule(beanType), true);

        File destFile = new File("/Users/admin/Desktop/"+fileName);
        writer.flush(destFile);
        System.out.println("write success!");

    }
    @Override
    public void getExcelTemplate(HttpServletResponse response,String fileName) throws Exception {

        try {
            InputStream in = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\"+fileName));

            // 1 ????????????
            final ExcelReader reader = ExcelUtil.getReader(in);

//            final ExcelReader reader = ExcelUtil.getReader(this.getClass().getResourceAsStream("exceltemplate/"+fileName));
            List<List<Object>> lists = reader.read(1);

            ExcelWriter writer = ExcelUtil.getWriter(true);
            writer.merge(5,"???????????????");

// ?????????????????????????????????????????????????????????
//            style.setBackgroundColor(IndexedColors.RED, false);
            //???????????????????????????
            Row row = writer.getOrCreateRow(1);
            CellStyle cs = writer.getStyleSet().getCellStyle();
            cs.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            row.setRowStyle(cs);

            writer.write(lists);

            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 2 ????????????
            ServletOutputStream outputStream = response.getOutputStream();
            // ??????IO???????????????????????????
            writer.flush(outputStream, true);
            writer.close();
            IoUtil.close(outputStream);
        } catch (IOException  e){
            logger.error("EducationServiceImpl [export] ????????????????????????", e);
            throw new Exception("??????Excel??????");
        }
    }



    /**
     * ?????????????????????
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

    /**
     * ??????????????????JsonProperty
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

    //?????????????????????
    @Override
    public <T> String importData(Class<T> beanType,InputStream inputStream) {
        ExcelReader excelReader = ExcelUtil.getReader(inputStream);

        //????????????1
        List<List<Object>> read = excelReader.read(2, excelReader.getRowCount());
        //System.out.println("??????:"+read);
        List<Excel> excels = new ArrayList<>();
        //?????????????????????
        for (int i = 0; i < read.size(); i++) {
            List list = read.get(i);
            Excel excel = new Excel();
            excel.setName(list.get(0).toString());
            excel.setAge(Integer.parseInt(list.get(1).toString()));
            excel.setHeight(Double.parseDouble(list.get(2).toString()));
            excel.setWeight(Double.parseDouble(list.get(3).toString()));
            excel.setEdu(list.get(4).toString());
            excel.setStatus(Integer.parseInt(list.get(5).toString()));
            excels.add(excel);
        }

        //????????????2
//        Field[] fields = beanType.getDeclaredFields();
//        for (Field field : fields) {
//            String headName = field.getDeclaredAnnotation(JsonProperty.class).value();
//            String fieldName = field.getName();
//            excelReader.addHeaderAlias(headName, fieldName);
//        }
//
//        List<T> data = excelReader.read(1, excelReader.getRowCount(),beanType);

        int re = excelMapper.insertList(excels);

        return "????????????"+re;
    }

}