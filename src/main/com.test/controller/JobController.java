package controller;

import com.alibaba.fastjson.JSON;
import model.Job;
import model.JsonList;
import model.JsonString;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.JobService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class JobController {
    @Autowired
    JobService jobService;

    @RequestMapping(value="/jobListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String jobListApp(HttpServletResponse response,int page,int type){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        Map<String,Object> map = new HashMap<>();
        try{
            page = (page-1)*20;
            map.put("page",page);
            map.put("row",20);
            map.put("type",type);
            List<Job> list = jobService.findAllWithType(map);
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

    @RequestMapping(value="/jobDetailApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyDetailApp(HttpServletResponse response,Job job){
        JsonString jsonString = new JsonString();
        try{
            Job one = jobService.findOneById(job);
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

    @RequestMapping(value="/jobListPageApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String jobListPageApp(HttpServletResponse response,String type,int page){
        JsonString jsonString = new JsonString();
        try{
            page = (page-1)*20;
            List<Job> list = jobService.findAllByPageAndType(page,type);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            jsonString.setData(list);
            return JSON.toJSONString(jsonString);
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/addJobApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addJobApp(Job job, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            jobService.addJob(job);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateJobApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateJobApp(Job job, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            jobService.updateJob(job);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteJobApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteJobApp(Job job, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            jobService.deleteJob(job);
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
