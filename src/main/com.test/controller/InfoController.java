package controller;

import com.alibaba.fastjson.JSON;
import model.Info;
import model.JsonList;
import model.JsonString;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.InfoService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class InfoController {
    @Autowired
    InfoService infoService;

    @RequestMapping(value="/infoListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infoListApp(HttpServletResponse response,int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        try{
            page = (page-1)*20;
            List<Info> list = infoService.findAll(page);
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

    @RequestMapping(value="/infoDetailApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infoDetailApp(HttpServletResponse response,Info info){
        JsonString jsonString = new JsonString();
        try{
            Info one = infoService.findOneById(info);
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

        @RequestMapping(value="/infoListPageApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infoListPageApp(HttpServletResponse response,int page,String type,
                                  @RequestParam(defaultValue = "0") int kind){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        Map<String,Object> map = new HashMap<>();
        try{
            //页码： (page-1)*20 , 20
            page = (page-1)*20;
            map.put("page",page);
            map.put("type",type);
            map.put("row",20);
            map.put("search",null);
            if(type.equals("6")){
                map.put("kind",kind);
            }
//            map.put("canEnroll",1);
            List<Info> list = infoService.findAllByPageAndType(map);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            jsonList.setList(list);
            jsonString.setData(jsonList);
            return JSON.toJSONString(jsonString);
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(StatusCode.CODE500);
            jsonString.setData(jsonList);
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/addInfoApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addInfoApp(Info info, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            infoService.addInfo(info);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateInfoApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateInfoApp(Info info, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            infoService.updateInfo(info);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteInfoApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteInfoApp(Info info, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            infoService.deleteInfo(info);
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
