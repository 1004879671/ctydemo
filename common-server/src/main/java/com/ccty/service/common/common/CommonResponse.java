package com.ccty.service.common.common;

//import com.github.pagehelper.PageInfo;

public class CommonResponse<T> extends PageContant {

    private String code;
    private String msg;
    private Object data;
//    private Data<T> data;

    public CommonResponse() {}

    public CommonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse success(Object data){
        CommonResponse commonResponse = new CommonResponse("200","SUCCESS",data);
        return commonResponse;

    }

//    public CommonResponse toPage(PageInfo pageResult){
//        CommonResponse commonResponse = new CommonResponse("200","SUCCESS",pageResult.getList());
//        commonResponse.setPageNum(pageResult.getPageNum());
//        commonResponse.setPageSize(pageResult.getPageSize());
//        commonResponse.setTotalSize(pageResult.getTotal());
//        commonResponse.setTotalPages(pageResult.getPages());
//        return commonResponse;
//
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
