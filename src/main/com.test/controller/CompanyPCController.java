package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CompanyPCController extends BaseController{

    @RequestMapping(value="/companyList")
    public String companyList(Model model, PageParam pageParam){
        Map<String,Object> map = new ReturnMap().getMap(pageParam);
        try{
            List<Company> list = companyService.findAll(map);
            model.addAttribute("companyList",list);
            int total = companyService.countAll(map);
            pageParam.setTotal(total);
            model.addAttribute("param",pageParam);
            return "companyList";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value="/companyListPage")
    public String companyListPageApp(Model model,HttpServletResponse response,int page){
        try{
            page = (page-1)*20;
            List<Company> list = companyService.findAllByPage(page);
            model.addAttribute("companyListPage",list);
            return "companyListPage";
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value="/companyDetail")
    public String jobDetail(Model model, Company company){
        if(company.getTid() != null){
            Company updateCompany = companyService.findOneById(company);
            model.addAttribute("updateCompany",updateCompany);
            return "companyDetail";
        }else {
            return "companyDetail";
        }
    }

    @RequestMapping(value="/addCompany",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addCompany(Company company, HttpServletResponse response){
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

    @RequestMapping(value="/updateCompany",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateCompany(Company company, HttpServletResponse response){
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

    @RequestMapping(value="/deleteCompany",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteCompany(Company company, HttpServletResponse response){
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
