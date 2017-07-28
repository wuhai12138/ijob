package controller;

import com.alibaba.fastjson.JSON;
import model.Apply;
import model.Job;
import model.JsonString;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ApplyService;
import service.JobService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class ApplyPCController {
    @Autowired
    ApplyService applyService;
    @Autowired
    JobService jobService;

    @RequestMapping(value="/applyList")
    public String applyList(HttpServletResponse response, Model model,String studentId,int page){
        JsonString jsonString = new JsonString();
        Map<String,Object> map = new HashMap<>();
        try{
            page = (page-1)*20;
            map.put("studentId",studentId);
            map.put("page",page);
            List<Apply> list = applyService.findAll(map);
            model.addAttribute("applyList",list);
            return "applyList";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/addApply",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addApply(Apply apply, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            applyService.addApply(apply);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateApply",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateApply(Apply apply, HttpServletResponse response){
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

    @RequestMapping(value="/deleteApply",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteApply(Apply apply, HttpServletResponse response){
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

    @RequestMapping(value="/applyJobList")
    public String applyJobList(Apply apply, Model model){
        List<Apply> applyList = applyService.findStudentByJobId(apply);
        model.addAttribute("applyList",applyList);
        return "applyJobList";
    }
}
