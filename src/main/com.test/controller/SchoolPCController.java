package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SchoolService;
import util.ReturnMap;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class SchoolPCController extends BaseController{

    @RequestMapping(value="/schoolList")
    public String schoolList(Model model, PageParam pageParam){
        Map<String,Object> map = new ReturnMap().getMap(pageParam);
        List<School> list = schoolService.findAll(map);
        model.addAttribute("schoolList",list);
        int total = schoolService.countAll(map);
        pageParam.setTotal(total);
        model.addAttribute("param",pageParam);
        return "schoolList";
    }

    @RequestMapping(value="/schoolDetail",produces = "text/html;charset=UTF-8")
    public String schoolDetail(Model model, School school){
        if(school.getTid() != null){
            School updateSchool = schoolService.findOneById(school);
            model.addAttribute("updateSchool",updateSchool);
            return "schoolDetail";
        }else {
            return "schoolDetail";
        }
    }

    @RequestMapping(value="/addSchool",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addSchool(School school, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            School checkSchool = schoolService.checkOneByName(school);
            if(checkSchool==null){
                schoolService.addSchool(school);
                jsonString.setStatus(200);
                jsonString.setInfo(StatusCode.CODE200);
                return JSON.toJSONString(jsonString);
            }else {
                jsonString.setStatus(1002);
                jsonString.setInfo(StatusCode.CODE1002);
                return JSON.toJSONString(jsonString);
            }
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateSchool",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateSchool(School school, HttpServletResponse response){
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

    @RequestMapping(value="/deleteSchool",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteSchool(School school, HttpServletResponse response){
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
