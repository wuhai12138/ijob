package controller;

import com.alibaba.fastjson.JSON;
import model.JsonList;
import model.JsonString;
import model.Student;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StudentService;
import util.CrossOrg;
import util.ReturnMap;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class  StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value="/studentListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String studentListApp(HttpServletResponse response,int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        Map<String,Object> map = new HashMap<>();
        try{
            page = (page-1)*20;
            map.put("page",page);
            map.put("row",20);
            List<Student> list = studentService.findAll(map);
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

    @RequestMapping(value="/studentDetailApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyDetailApp(HttpServletResponse response,Student student){
        JsonString jsonString = new JsonString();
        try{
            Student one = studentService.findOneById(student);
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

    @RequestMapping(value="/addStudentApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addStudentApp(Student student, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            studentService.addStudent(student);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateStudentApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateStudentApp(Student student, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            studentService.updateStudent(student);
            jsonString.setData(new Object());
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setData(new Object());
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteStudentApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteStudentApp(Student student, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            studentService.deleteStudent(student);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/loginApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginApp(Student student, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            Student currentUser = studentService.findOneByCodeAndPassword(student);
            if(currentUser == null){
                jsonString.setStatus(1004);
                jsonString.setInfo(StatusCode.CODE1004);
                return JSON.toJSONString(jsonString);
            }else{
                jsonString.setStatus(200);
                jsonString.setInfo(StatusCode.CODE200);
                jsonString.setData(currentUser);
                return JSON.toJSONString(jsonString);
            }
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/ckeckNick",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String ckeckNick (Student student,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        JsonString jsonString = new JsonString();
        try{
            Student currentUser = studentService.findOneByCode(student);
            return JSON.toJSONString(currentUser.getNick());
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }
}
