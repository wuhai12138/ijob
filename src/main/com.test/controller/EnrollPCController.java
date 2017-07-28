package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.EnrollService;
import service.InfoService;
import service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class EnrollPCController {
    @Autowired
    EnrollService enrollService;
    @Autowired
    StudentService studentService;
    @Autowired
    InfoService infoService;

    @RequestMapping(value="/enrollList")
    public String enrollList(Model model,HttpServletResponse response,int page){
        try{
            page = (page-1)*20;
            List<Enroll> list = enrollService.findAll(page);
            model.addAttribute("enrollList",list);
            return "enrollList";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value="/addEnroll",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addEnroll(Enroll enroll, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
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
                    jsonString.setStatus(200);
                    jsonString.setInfo(StatusCode.CODE200);
                    return JSON.toJSONString(jsonString);
                }else{
                    jsonString.setStatus(1002);
                    jsonString.setInfo(StatusCode.CODE1002);
                    return JSON.toJSONString(jsonString);
                }
            }
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateEnroll",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateEnroll(Enroll enroll, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            enrollService.updateEnroll(enroll);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteEnroll",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteEnroll(Enroll enroll, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            enrollService.deleteEnroll(enroll);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/enrollInfoList")
    public String enrollInfoList(Enroll enroll,Model model){
        List<Enroll> enrollList = enrollService.findAllByInfoId(enroll);
        model.addAttribute("enrollList",enrollList);
        return "enrollInfoList";
    }
}
