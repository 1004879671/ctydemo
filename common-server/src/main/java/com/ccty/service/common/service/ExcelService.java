package com.ccty.service.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccty.service.common.entity.Excel;
import com.ccty.service.common.entity.dto.ExcelDto;
import com.ccty.service.common.entity.dto.ExcelVo;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public interface ExcelService extends IService<Excel> {
    List<Excel> qryExcelList(ExcelDto excelDto );

    void getExcelTemplate(HttpServletResponse response, String fileName) throws Exception;

    <T> String importData(Class<T> beanType, InputStream inputStream);
}
