package controller;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.Job;
import model.JsonString;
import model.PageParam;
import model.StatusCode;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.JobService;
import util.ImageUpload;
import util.ReturnMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class JobPCController extends BaseController{

    @RequestMapping(value="/jobList")
    public String jobList(Model model, PageParam pageParam){
        Map<String,Object> map = new ReturnMap().getMap(pageParam);
            List<Job> list = jobService.findAll(map);
            model.addAttribute("jobList",list);
            int total = jobService.countAll(map);
            pageParam.setTotal(total);
            model.addAttribute("param",pageParam);
            return "jobList";
    }

    @RequestMapping(value="/jobListPage")
    public String jobListPage(Model model, HttpServletResponse response,String type,int page){
        try{
            page = (page-1)*20;
            List<Job> list = jobService.findAllByPageAndType(page,type);
            model.addAttribute("jobListPage",list);
            return "jobListPage";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value="/jobDetail",produces = "text/html;charset=UTF-8")
    public String jobDetail(Model model, Job job){
        if(job.getTid() != null){
            Job updateJob = jobService.findOneById(job);
            model.addAttribute("updateJob",updateJob);
            return "jobDetail";
        }else {
            return "jobDetail";
        }
    }

    @RequestMapping(value="/addJob",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addJob(Job job, HttpServletRequest request,
                         @RequestParam(value = "file", required = false) MultipartFile file) throws IOException{
        String fileName = new ImageUpload().ImgUpload(file,request);
        job.setImage(fileName);
        jobService.addJob(job);
        return "success";
    }

    @RequestMapping(value="/updateJob",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateJob(Job job, HttpServletRequest request,
                            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException{
        String fileName = new ImageUpload().ImgUpload(file,request);
        job.setImage(fileName);
        jobService.updateJob(job);
        return "success";
    }

    @RequestMapping(value="/deleteJob",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteJob(Job job, HttpServletRequest request){
        JsonString jsonString = new JsonString();
        try{
            Job ImgInfo = jobService.findOneById(job);
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

    @RequestMapping(value = "/exprotJob")
    @ResponseBody
    public String exprotJob(HttpServletResponse response) throws IOException{
        List<Job> jobList = jobService.findAllWithApply();
        int totalsize=jobList.size();

        String name = "职位申请列表";
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet(name);

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        //创建数据名称行
        HSSFRow rowIndex=sheet.createRow(0);
//		if(qx.equals("超级管理员")){
        if(jobList.size()!=0){
            String[] title = {"职位要求","联系方式","职位描述","企业名称","职位名称","发布时间","学生账号","学生","班级","申请日期"};
            for(int j=0;j<title.length;j++){
                rowIndex.createCell(j).setCellValue(title[j]);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(int i=1;i<=totalsize;i++){
                HSSFRow row=sheet.createRow(i);
                Job pojo = jobList.get(i-1);
                row.createCell(0).setCellValue(pojo.getRequirement()!=null?pojo.getRequirement():"");
                row.createCell(1).setCellValue(pojo.getPhone()!=null?pojo.getPhone():"");
                row.createCell(2).setCellValue(pojo.getDescription()!=null?pojo.getDescription():"");
                row.createCell(3).setCellValue(pojo.getCompany()!=null?pojo.getCompany():"");
                row.createCell(4).setCellValue(pojo.getName()!=null?pojo.getName():"");
                row.createCell(5).setCellValue(sdf.format(pojo.getCreateTime()));
                row.createCell(6).setCellValue(pojo.getCode()!=null?pojo.getCode():"");
                row.createCell(7).setCellValue(pojo.getStudentName()!=null?pojo.getStudentName():"");
                row.createCell(8).setCellValue(pojo.getIntroduction()!=null?pojo.getIntroduction():"");
                row.createCell(9).setCellValue(pojo.getApplyTime()!=null?pojo.getApplyTime():"");
            }
        }
        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String dat = sdf.format(new Date());
        dat = "职位申请"+dat;
        response.setHeader("Content-disposition", "attachment; filename="+new String((dat+".xls").getBytes(),"iso8859-1"));
        response.setContentType("application/msexcel;charset=ISO8859_1");
        wb.write(output);
        output.close();
        return null;
    }
}
