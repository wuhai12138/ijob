package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.InfoService;
import util.ImageUpload;
import util.ReturnMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class InfoPCController {
    @Autowired
    InfoService infoService;

    @RequestMapping(value="/infoList")
    public String infoList(Model model, HttpServletResponse response,int page){
        JsonString jsonString = new JsonString();
        try{
            page = (page-1)*20;
            List<Info> list = infoService.findAll(page);
            model.addAttribute("infoList",list);
            return "infoList";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value="/infoListPage")
    public String infoListPage(Model model, PageParam pageParam, String type,String types){
        Map<String,Object> map = new ReturnMap().getMap(pageParam);
        try{
            map.put("type",type);
            if(types!=null){
                map.put("types",types);
            }
            List<Info> list = infoService.findAllByPageAndType(map);
            int total = infoService.countAllByType(map);
            pageParam.setTotal(total);
            model.addAttribute("infoList",list);
            model.addAttribute("param",pageParam);
            if(type.equals("1")){
                return "newsList";
            }else if(type.equals("3")){
                return "gradeList";
            }else if(type.equals("2")){
                return "speechList";
            }else if(type.equals("4")){
                return "careerList";
            }else if(type.equals("6")){
                return "startBusinessList";
            }else if(type.equals("7")){
                return "colourfulList";
            }else {
                return "infoListPage";
            }
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value="/infoDetail",produces = "text/html;charset=UTF-8")
    public String infoDetail(Model model, Info info){
        if(info.getTid() != null){
            Info updateInfo = infoService.findOneById(info);
            model.addAttribute("updateInfo",updateInfo);
            return "infoDetail";
        }else {
            model.addAttribute("type",info.getType());
            return "infoDetail";
        }
    }

    @RequestMapping(value="/careerDetail",produces = "text/html;charset=UTF-8")
    public String careerDetail(Model model, Info info){
        if(info.getTid() != null){
            Info updateInfo = infoService.findOneById(info);
            model.addAttribute("updateCareer",updateInfo);
            return "careerDetail";
        }else {
            return "careerDetail";
        }
    }

    @RequestMapping(value="/colourfulDetail",produces = "text/html;charset=UTF-8")
    public String colourfulDetail(Model model, Info info){
        if(info.getTid() != null){
            Info updateInfo = infoService.findOneById(info);
            model.addAttribute("updateColourful",updateInfo);
            return "colourfulDetail";
        }else {
            return "colourfulDetail";
        }
    }

    @RequestMapping(value="/startBusinessDetail",produces = "text/html;charset=UTF-8")
    public String startBusinessDetail(Model model, Info info){
        if(info.getTid() != null){
            Info updateInfo = infoService.findOneById(info);
            model.addAttribute("updateBus",updateInfo);
            return "startBusinessDetail";
        }else {
            model.addAttribute("type",6);
            return "startBusinessDetail";
        }
    }

    @RequestMapping(value="/addInfo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addInfo(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file,
                          Info info) throws IOException{
        String fileName = new ImageUpload().ImgUpload(file,request);
        info.setImg(fileName);
        info.setTitle(URLDecoder.decode(info.getTitle(),"UTF-8"));
        info.setLink(URLDecoder.decode(info.getLink(),"UTF-8"));
        infoService.addInfo(info);
        return "success";
    }

    @RequestMapping(value="/updateInfo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateInfo(Info info, @RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request) throws IOException{
        String fileName = new ImageUpload().ImgUpload(file,request);
        info.setImg(fileName);
        info.setTitle(URLDecoder.decode(info.getTitle(),"UTF-8"));
        info.setLink(URLDecoder.decode(info.getLink(),"UTF-8"));
        infoService.updateInfo(info);
        return "success";
    }

    @RequestMapping(value="/deleteInfo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteInfo(Info info, HttpServletRequest request){
        JsonString jsonString = new JsonString();
        try{
            Info ImgInfo = infoService.findOneById(info);
            infoService.deleteInfo(info);
            String path = ImgInfo.getImg();
            new ImageUpload().deleteFile(request,path);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/changeInfoStatus",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String changeInfoStatus(Info info, HttpServletRequest request){
        JsonString jsonString = new JsonString();
        try{
            infoService.changeInfoStatus(info);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value = "/exportCareer")
    @ResponseBody
    public String exportCareer(HttpServletResponse response) throws IOException{
        List<Info> infos = infoService.findAllWithEnroll();
        int totalsize=infos.size();

        String name = "报名列表";
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet(name);

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        //创建数据名称行
        HSSFRow rowIndex=sheet.createRow(0);
//		if(qx.equals("超级管理员")){
        if(infos.size()!=0){
            String[] title = {"标题","链接","学生","班级","申请日期"};
            for(int j=0;j<title.length;j++){
                rowIndex.createCell(j).setCellValue(title[j]);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(int i=1;i<=totalsize;i++){
                HSSFRow row=sheet.createRow(i);
                Info pojo = infos.get(i-1);
                row.createCell(0).setCellValue(pojo.getTitle()!=null?pojo.getTitle():"");
                row.createCell(1).setCellValue(pojo.getLink()!=null?pojo.getLink():"");
                row.createCell(2).setCellValue(pojo.getName()!=null?pojo.getName():"");
                row.createCell(3).setCellValue(pojo.getIntroduction()!=null?pojo.getIntroduction():"");
                row.createCell(4).setCellValue(pojo.getCreateTime()!=null?sdf.format(pojo.getCreateTime()):"");
            }
        }
        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String dat = sdf.format(new Date());
        dat = "报名列表"+dat;
        response.setHeader("Content-disposition", "attachment; filename="+new String((dat+".xls").getBytes(),"iso8859-1"));
        response.setContentType("application/msexcel;charset=ISO8859_1");
        wb.write(output);
        output.close();
        return null;
    }

    @RequestMapping(value = "/exportBus")
    @ResponseBody
    public String exportBus(HttpServletResponse response,int type) throws IOException{
        List<Info> infos = infoService.findAllWithType(type);
        int totalsize=infos.size();

        String name = "报名列表";
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet(name);

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        //创建数据名称行
        HSSFRow rowIndex=sheet.createRow(0);
//		if(qx.equals("超级管理员")){
        if(infos.size()!=0){
            String[] title = {"标题","链接","学生","班级","申请日期"};
            for(int j=0;j<title.length;j++){
                rowIndex.createCell(j).setCellValue(title[j]);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(int i=1;i<=totalsize;i++){
                HSSFRow row=sheet.createRow(i);
                Info pojo = infos.get(i-1);
                row.createCell(0).setCellValue(pojo.getTitle()!=null?pojo.getTitle():"");
                row.createCell(1).setCellValue(pojo.getLink()!=null?pojo.getLink():"");
                row.createCell(2).setCellValue(pojo.getName()!=null?pojo.getName():"");
                row.createCell(3).setCellValue(pojo.getIntroduction()!=null?pojo.getIntroduction():"");
                row.createCell(5).setCellValue(pojo.getCreateTime()!=null?sdf.format(pojo.getCreateTime()):"");
            }
        }
        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String dat = sdf.format(new Date());
        dat = "报名列表"+dat;
        response.setHeader("Content-disposition", "attachment; filename="+new String((dat+".xls").getBytes(),"iso8859-1"));
        response.setContentType("application/msexcel;charset=ISO8859_1");
        wb.write(output);
        output.close();
        return null;
    }
}
