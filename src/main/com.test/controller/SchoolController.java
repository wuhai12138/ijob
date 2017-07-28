package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SchoolService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @RequestMapping(value="/schoolListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String schoolListApp(HttpServletResponse response,int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        try{
            page = (page-1)*20;
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("page",page);
            List<School> list = schoolService.findAll(map);
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

    @RequestMapping(value="/schoolDetailApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyDetailApp(HttpServletResponse response,School school){
        JsonString jsonString = new JsonString();
        try{
            School one = schoolService.findOneById(school);
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

    @RequestMapping(value="/addSchoolApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addSchoolApp(School school, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            schoolService.addSchool(school);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateSchoolApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateSchoolApp(School school, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            schoolService.updateSchool(school);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteSchoolApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteSchoolApp(School school, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            schoolService.deleteSchool(school);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }
}
