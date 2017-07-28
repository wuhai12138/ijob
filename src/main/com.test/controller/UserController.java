package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class UserController extends BaseController{

    @RequestMapping(value="/userListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String userListApp(HttpServletResponse response){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        try{
            List<User> list = userService.findAllUser();
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

    @RequestMapping(value="/userDetailApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyDetailApp(HttpServletResponse response,User user){
        JsonString jsonString = new JsonString();
        try{
            User one = userService.findOneById(user);
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

    @RequestMapping(value="/addUserApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUserApp(User user, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            User checkUser = userService.findOneByName(user);
            if(checkUser != null){
                jsonString.setStatus(1002);
                jsonString.setInfo(StatusCode.CODE1002);
                return JSON.toJSONString(jsonString);
            }else{
                userService.addUser(user);
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

    @RequestMapping(value="/updateUserApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUserApp(User user, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            userService.updateUser(user);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteUserApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteUserApp(User user, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            userService.deleteUser(user);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/login", produces = "text/html;charset=UTF-8")
    public String login(HttpServletResponse response){
        try{
            return "login";
        }catch(Exception e){
            return "error";
        }
    }
    @RequestMapping(value="/main", produces = "text/html;charset=UTF-8")
    public String log(User user,Model model,HttpServletRequest request){
        try{
            if(user.getUserName()==null){
                return "login";
            }else{
                User currentUser = userService.findOneByNameAndPwd(user);
                if(currentUser!=null){
                    model.addAttribute("currentUser",currentUser);
//                    拿到TOKEN
                    IMToken imToken = new IMToken();
                    String url = "https://a1.easemob.com/ijob-dh/ijob/token";
                    String res = HttpUtil.postToken(url,imToken);
                    if (res == null) {
                        return "login";
                    }else {
                        res = res.substring(res.indexOf(":")+2,res.indexOf(",")-1);
//                        String res = "YWMtaTCSvmkPEea8SCU94XwttQAAAVfrmpW3qGOqlXT23HhMvFP92srYTrmyAew"; //TOKEN
                        request.getSession().setAttribute("token",res);
                    }
                    return "main";
                }else {
                    return "login";
                }
            }
        }catch(Exception e){
            System.out.println(e);
            return "error";
        }
    }

    //测试
    @RequestMapping(value="/error")
    public String error(Model model){
        try{
            return "error";
        }catch(Exception e){
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }
}
