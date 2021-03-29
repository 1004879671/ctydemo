package com.ccty.service.common.util;


public class AppFilePathConstance {
    private String root;
    private String home;
    private String excelTemplate;
    private String excelExport;
    private String pdfTemplate;
    private String attachment;
    private String area;

    private static AppFilePathConstance instance;



    private  String userHome = System.getProperty("user.home");

    private String local = System.getProperty("user.dir");


    private AppFilePathConstance(String[] args) {
        this.root = local + "/" +args[0];
        this.home = this.root + "/" +args[1];
        this.excelTemplate = this.root + "/" +args[2];
        this.excelExport = this.root + "/" +args[3];
        this.pdfTemplate = this.root + "/" +args[4];
        this.attachment = this.root + "/" +args[5];
        this.area = this.root + "/" +args[6];
    }

    public static synchronized  AppFilePathConstance getInstance(String[] args){
        if(instance==null){
            instance = new AppFilePathConstance(args);
            FileUtil.bathCreateFolder(new String[]{instance.home,instance.excelTemplate,instance.excelExport,
                    instance.pdfTemplate, instance.attachment,instance.area});
        }
        return instance;
    }

    public static String getRoot() {
        return instance.root;
    }

    public static String getHome() {
        return instance.home;
    }

    public static String getExcelTemplate() {
        return instance.excelTemplate;
    }

    public static String getExcelExport() {
        return instance.excelExport;
    }

    public static String getPdfTemplate() {
        return instance.pdfTemplate;
    }

    public static String getAttachment() {
        return instance.attachment;
    }

    public static String getArea() {
        return instance.area;
    }
}
