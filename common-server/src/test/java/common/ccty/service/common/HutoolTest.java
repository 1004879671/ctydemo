package common.ccty.service.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HutoolTest {
    @Test
    public void test1(){

    }
    public static void main(String[] args) throws ParseException {

        //Convert
        Date date = Convert.toDate("2016/02/03");//快速格式化时间
        Date date2 = Convert.toDate("2019-03-03");
        System.out.println(date2.before(date));
        System.out.println(date2.after(date));
        System.out.println(DateUtil.format(new Date(),"yyyy/MM/dd HH:mm:ss"));
        System.out.println(DateUtil.formatDateTime(date));

        String numberToChinese = Convert.numberToChinese(54, true);//数字转中文
        String digitToChinese = Convert.digitToChinese(32.24);//叁拾贰元贰角肆分

        //DateUtil
        String now = DateUtil.now();//获取当前时间 转为String类型
        DateTime date3 = DateUtil.date();//获取当前时间为DateTime类型
        long day = DateUtil.between(date2, date, DateUnit.DAY);//计算相差多少天
        long week = DateUtil.between(date2, date, DateUnit.WEEK);//计算相差多少中
        System.out.println(DateUtil.age(date, date2));//计算年龄
        System.out.println(DateUtil.ageOfNow(date));//计算出生到现在的年龄
        System.out.println("******"+DateUtil.beginOfDay(date));
        System.out.println(week);

        //CaptchaUtil 生成验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(50, 80);

        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(80, 80);

        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(50, 80);
        System.out.println(shearCaptcha.getCode());
        BufferedImage image = lineCaptcha.getImage();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(new Date());
        sdf.parse("2020-12-18 00:00:00");


    }
}