package model;

/**
 * Created by Administrator on 2016/7/19.
 */
public class StatusCode {
    public final static String CODE200 = "执行正常";
    public final static String CODE500 = "内部错误";
    public final static String CODE1000 = "找不到该数据";
    public final static String CODE1001 = "密码错误";
    public final static String CODE1002 = "账号已存在";
    public final static String CODE1003 = "ALREADY GET JOB";
    public final static String CODE1004 = "用户名密码错误";
    public final static String LINUXPATH = "/usr/local/tomcat/temp/jobImage";
    public final static String WINDOWSPATH = "D:\\apache-tomcat-8.0.33\\temp\\jobImage";

    public static String getCODE200() {
        return CODE200;
    }

    public static String getCODE500() {
        return CODE500;
    }

    public static String getCODE1000() {
        return CODE1000;
    }

    public static String getCODE1001() {
        return CODE1001;
    }

    public static String getCODE1002() {
        return CODE1002;
    }

    public static String getCODE1003() { return CODE1003; }

    public static String getCODE1004() { return CODE1004; }

    public static String getLINUXPATH() {
        return LINUXPATH;
    }
}
