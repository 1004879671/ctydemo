package com.ccty.service.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccty.service.common.entity.Excel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExcelMapper extends BaseMapper<Excel> {

    /**
     * 批量插入
     * @param recordList
     * @return
     */
    int insertList(List<Excel> recordList);

}
