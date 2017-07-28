package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ApplyService;
import service.JobService;
import util.HttpUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class ApplyController extends BaseController{

    @RequestMapping(value="/applyListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyListApp(HttpServletResponse response,String studentId,int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        Map<String,Object> map = new HashMap<>();
        try{
            page = (page-1)*20;
            map.put("studentId",studentId);
            map.put("page",page);
            List<Apply> list = applyService.findAll(map);
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

    @RequestMapping(value="/applyDetailApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyDetailApp(HttpServletResponse response,Apply apply){
        JsonString jsonString = new JsonString();
        try{
            Apply one = applyService.findOneById(apply);
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

    @RequestMapping(value="/addApplyApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addApplyApp(Apply apply, HttpServletResponse response,String code){
        JsonString jsonString = new JsonString();
        Job job = new Job();
        job.setTid(Long.valueOf(apply.getJobId()));
        Job job1 = jobService.findOneById(job);
        int count = applyService.countByJobId(apply.getJobId());
        if(count >= job1.getLimitNum()){
            jsonString.setStatus(500);
            jsonString.setInfo("人数已满");
            jsonString.setData(new Object());
            return JSON.toJSONString(jsonString);
        }else {
            try {
                Student student = new Student();
                student.setTid(Long.parseLong(apply.getStudentId()));
                Student checkStatus = studentService.findOneById(student);
                if ("1".equals(checkStatus.getStatus())) {
                    jsonString.setStatus(500);
                    jsonString.setInfo("已被录取");
                    jsonString.setData(new Object());
                    return JSON.toJSONString(jsonString);
                } else {
                    Apply checkApply = applyService.checkDuplicate(apply);
                    if (checkApply != null) {
                        jsonString.setStatus(500);
                        jsonString.setInfo("不可重复申请");
                        jsonString.setData(new Object());
                        return JSON.toJSONString(jsonString);
                    } else {
                        apply.setState("0");
                        applyService.addApply(apply);
                        //调用接口 就业报名
                        int codes = HttpUtil.scorePost(code, "就业报名所得");
                        jsonString.setStatus(200);
                        jsonString.setInfo(StatusCode.CODE200);
                        jsonString.setData(new Object());
                        return JSON.toJSONString(jsonString);
                    }
                }
            } catch (Exception e) {
                jsonString.setStatus(500);
                jsonString.setInfo(e.getMessage());
                jsonString.setData(new Object());
                return JSON.toJSONString(jsonString);
            }
        }
    }

    @RequestMapping(value="/updateApplyApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateApplyApp(Apply apply, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            applyService.updateApply(apply);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteApplyApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteApplyApp(Apply apply, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            applyService.deleteApply(apply);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/applyJobListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyJobListApp(Apply apply, HttpServletResponse response, int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        Map<String,Object> map = new HashMap<>();
        try{
            page = (page-1)*20;
            map.put("studentId",apply.getStudentId());
            map.put("page",page);
            List<Job> jobList = jobService.findAllByStudentId(map);
            jsonList.setList(jobList);
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
