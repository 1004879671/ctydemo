package com.ccty.service.common.common;

import com.fasterxml.jackson.annotation.JsonInclude;
//import com.github.pagehelper.PageInfo;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Data(){}

//    public Data(T obj, PageInfo pageInfo) {
//        this.obj = obj;
//        this.pageInfo = pageInfo;
//    }

    private T obj ;
//    private PageInfo pageInfo;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

//    public PageInfo getPageInfo() {
//        return pageInfo;
//    }
//
//    public void setPageInfo(PageInfo pageInfo) {
//        this.pageInfo = pageInfo;
//    }
}
