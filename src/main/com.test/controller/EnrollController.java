package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.EnrollService;
import service.InfoService;
import service.StudentService;
import util.HttpUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class EnrollController {
    @Autowired
    EnrollService enrollService;
    @Autowired
    StudentService studentService;
    @Autowired
    InfoService infoService;

    @RequestMapping(value="/enrollListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String enrollListApp(HttpServletResponse response,int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        try{
            page = (page-1)*20;
            List<Enroll> list = enrollService.findAll(page);
            jsonList.setList(list);
            if(list.size()>0){
                jsonString.setStatus(200);
                jsonString.setInfo(StatusCode.CODE200);
                jsonString.setData(jsonList);
                return JSON.toJSONString(jsonString);
            }else{
                jsonString.setStatus(1000);
                jsonString.setInfo(StatusCode.CODE1000);
                return JSON.toJSONString(jsonString);
            }
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/enrollDetailApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String enrollDetailApp(HttpServletResponse response,Enroll enroll){
        JsonString jsonString = new JsonString();
        try{
            Enroll one = enrollService.findOneById(enroll);
            if(one!=null){
                jsonString.setStatus(200);
                jsonString.setInfo(StatusCode.CODE200);
                jsonString.setData(one);
                return JSON.toJSONString(jsonString);
            }else{
                jsonString.setStatus(1000);
                jsonString.setInfo(StatusCode.CODE1000);
                return JSON.toJSONString(jsonString);
            }
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/addEnrollApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addEnrollApp(Enroll enroll, HttpServletResponse response,String code){
        JsonString jsonString = new JsonString();
        System.out.println("------------------"+"code"+code+"------------------");
        Info info = new Info();
        info.setTid(Long.valueOf(enroll.getInfoId()));
        Info checkThis = infoService.findOneById(info);
        int count = enrollService.countByInfoId(Integer.valueOf(enroll.getInfoId()));
        if(count>=checkThis.getLimitNum()){
            jsonString.setStatus(500);
            jsonString.setInfo("人数已满");
            return JSON.toJSONString(jsonString);
        }

        Student student = new Student();
        student.setTid(Long.parseLong(enroll.getStudentId()));
        Student checkStudent = studentService.findOneById(student);
        if(checkStudent.getStatus()=="2"){
            jsonString.setStatus(1003);
            jsonString.setInfo(StatusCode.CODE1003);
            return JSON.toJSONString(jsonString);
        }else{
            Enroll enrollCheck = enrollService.checkOne(enroll);
            if(enrollCheck==null){
                enrollService.addEnroll(enroll);
                //调用接口 活动报名
                int codes = HttpUtil.scorePost(code,"活动报名所得");
                jsonString.setData(new Object());
                jsonString.setStatus(200);
                jsonString.setInfo(StatusCode.CODE200);
                return JSON.toJSONString(jsonString);
            }else{
                jsonString.setData(new Object());
                jsonString.setStatus(1002);
                jsonString.setInfo("不可重复申请");
                return JSON.toJSONString(jsonString);
            }
        }
    }

    @RequestMapping(value="/updateEnrollApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateEnrollApp(Enroll enroll, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            enrollService.updateEnroll(enroll);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            jsonString.setData(new Object());
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            jsonString.setData(new Object());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteEnrollApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteEnrollApp(Enroll enroll, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            enrollService.deleteEnroll(enroll);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            jsonString.setData(new Object());
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            jsonString.setData(new Object());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/enrollInfoListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String enrollInfoListApp(Enroll enroll, HttpServletResponse response, int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        try{
            page = (page-1)*20;
            List<Info> infoList = infoService.findAllByStudentId(enroll.getStudentId(),page);
            jsonList.setList(infoList);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.getCODE200());
            jsonString.setData(jsonList);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }
}
