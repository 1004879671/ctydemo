package com.ccty.service.common.config;

import com.ccty.service.common.util.AppFilePathConstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppFileConfig {
    @Value("${ccty.appfile.root}")
    private String root;

    @Value("${ccty.appfile.home}")
    private String home;

    @Value("${ccty.appfile.excelTemplate}")
    private String excelTemplate;

    @Value("${ccty.appfile.excelExport}")
    private String excelExport;

    @Value("${ccty.appfile.pdfTemplate}")
    private String pdfTemplate;

    @Value("${ccty.appfile.attachment}")
    private String attachment;

    @Value("${ccty.appfile.area}")
    private String area;

    @Bean
    public AppFilePathConstance appFilePathConstance(){
        AppFilePathConstance appFilePathConstance = AppFilePathConstance.getInstance(new String[]{
                root,home,excelTemplate,excelExport,pdfTemplate,attachment,area});
        return appFilePathConstance;
    }
}
