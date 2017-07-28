package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CompanyService;
import util.ReturnMap;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @RequestMapping(value="/companyListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String companyListApp(PageParam pageParam){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        Map<String,Object> map = new ReturnMap().getMap(pageParam);
        try{
            List<Company> list = companyService.findAll(map);
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

    @RequestMapping(value="/companyDetailApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String companyDetailApp(HttpServletResponse response,Company company){
        JsonString jsonString = new JsonString();
        try{
            Company one = companyService.findOneById(company);
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

    @RequestMapping(value="/companyListPageApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String companyListPageApp(HttpServletResponse response,int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        try{
            page = (page-1)*20;
            List<Company> list = companyService.findAllByPage(page);
            jsonList.setList(list);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            jsonString.setData(jsonList);
            return JSON.toJSONString(jsonString);
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/addCompanyApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addCompanyApp(Company company, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            Company checkCompany = companyService.findOneByName(company);
            if(checkCompany != null){
                jsonString.setStatus(1002);
                jsonString.setInfo(StatusCode.CODE1002);
                return JSON.toJSONString(jsonString);
            }else{
                companyService.addCompany(company);
                jsonString.setStatus(200);
                jsonString.setInfo(StatusCode.CODE200);
                return JSON.toJSONString(jsonString);
            }
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateCompanyApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateCompanyApp(Company company, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            companyService.updateCompany(company);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteCompanyApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteCompanyApp(Company company, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            companyService.deleteCompany(company);
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
