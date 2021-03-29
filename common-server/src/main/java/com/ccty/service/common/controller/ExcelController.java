package com.ccty.service.common.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ccty.service.common.entity.Excel;
import com.ccty.service.common.entity.dto.ExcelDto;
import com.ccty.service.common.entity.dto.ExcelVo;
import com.ccty.service.common.service.ExcelService;
import com.ccty.service.common.util.AppFilePathConstance;
import com.ccty.service.common.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Api(tags="导入导出接口测试")
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/toHtml")
    public String test(HttpServletRequest request) {
        return "excelImport";
    }

    @ApiOperation("导入测试")
    //处理文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        System.out.println(file);

        if (file.isEmpty()) {
            System.out.println("文件为空！");
            return "excelImport";
        }

        // 1.获取上传文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            //return ResponseData.fail(ResponseCodeEnum.ERROR_PARAM_INVALID);
            e.printStackTrace();
        }
//        // 2.应用HUtool ExcelUtil获取ExcelReader指定输入流和sheet
//        ExcelReader excelReader = ExcelUtil.getReader(inputStream, "导入材料清单");
//        // 可以加上表头验证
//        // 3.读取第二行到最后一行数据
//        List<List<Object>> read = excelReader.read(2, excelReader.getRowCount());
//        for (List<Object> objects : read) {
//            // objects.get(0),读取某行第一列数据
//            // objects.get(1),读取某行第二列数据
//            System.out.println(objects.get(0));
//        }


        //调用用 hutool 方法读取数据 默认调用第一个sheet
//        ExcelReader excelReader = ExcelUtil.getReader(inputStream);
        excelService.importData(ExcelDto.class,inputStream);


        //从第二行开始获取数据   excelReader.read的结果是一个2纬的list，外层是行，内层是行对应的所有列
        //读取方式1
//        List<List<Object>> read = excelReader.read(1, excelReader.getRowCount());
//        //System.out.println("数据:"+read);
//        List<Excel> excels = new ArrayList<>();
//        //循环获取的数据
//        for (int i = 0; i < read.size(); i++) {
//            List list = read.get(i);
//            Excel excel = new Excel();
//            excel.setName(list.get(0).toString());
//            excel.setAge(Integer.parseInt(list.get(1).toString()));
//            excel.setHeight(Double.parseDouble(list.get(2).toString()));
//            excel.setWeight(Double.parseDouble(list.get(3).toString()));
//            excel.setEdu(list.get(4).toString());
//            excel.setStatus(Integer.parseInt(list.get(5).toString()));
//            excels.add(excel);
//        }

        //读取方式2
        //读取为Bean列表，Bean中的字段名为标题，字段值为标题对应的单元格值。

//        List<Excel> excels = excelReader.readAll(Excel.class);
//        service.addList(excels);
        System.out.println("导入成功");
        return "excelImport";

    }

    @ApiOperation("导出测试")
    @GetMapping("/export")
    public void export(HttpServletResponse response, ExcelDto excelDto) throws UnsupportedEncodingException {

        List<Excel> excelList = excelService.qryExcelList(excelDto);
        List<ExcelVo> excelVos = new ArrayList<>();
       excelList.stream()
               .forEach(item->{
                  final  ExcelVo excelVo =  new ExcelVo();
                  BeanUtils.copyProperties(item,excelVo);
                  excelVos.add(excelVo);
       });

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("name", "用户名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("height", "身高");
        writer.addHeaderAlias("weight", "体重");
        writer.addHeaderAlias("edu", "教育情况");
        writer.addHeaderAlias("status", "状态");
        writer.addHeaderAlias("updateTime", "更新时间");
        writer.addHeaderAlias("createTime", "创建时间");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, "用户信息表");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(excelVos, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        String name = URLEncoder.encode("用户信息表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xlsx");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    @ApiOperation("模板下载测试")
    @GetMapping("/downModel")
    @ResponseBody
    public void downModel(HttpServletResponse response,@ApiParam("文件名")  @RequestParam  String fileName) throws Exception {
        excelService.getExcelTemplate(response,fileName);
    }

    @ApiOperation("模板下载测试1")
    @GetMapping("/load1")
    @ResponseBody
    public void load1(HttpServletResponse response,@ApiParam("文件名")  @RequestParam  String fileName) throws Exception {
        String filePath = AppFilePathConstance.getExcelTemplate()+"/"+fileName;
        FileUtil.downLoadFileNm(response,filePath,fileName);
    }

    @ApiOperation("模板下载测试2")
    @GetMapping("/load2")
    @ResponseBody
    public void load2(HttpServletResponse response,@ApiParam("文件名")  @RequestParam  String fileName) throws Exception {
        String filePath = AppFilePathConstance.getExcelTemplate()+"/"+fileName;
        FileUtil.downFileNm(response,filePath,fileName);

    }





}