package controller;

import com.alibaba.fastjson.JSON;
import model.JsonString;
import model.School;
import model.StatusCode;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class UserPCController extends BaseController {

    @RequestMapping(value="/userList",produces = "text/html;charset=UTF-8")
    public String userList(Model model){
        try{
            List<User> list = userService.findAllUser();
            model.addAttribute("userList",list);
            return "userList";
        }catch (Exception e){
            model.addAttribute("error",e);
            return "error";
        }
    }

    @RequestMapping(value="/userDetail",produces = "text/html;charset=UTF-8")
    public String userDetail(Model model, User user){
        if(user.getTid() != null){
            User updateUser = userService.findOneById(user);
            model.addAttribute("updateUser",updateUser);
            List<School> schoolList = schoolService.findAllWithOutPage();
            model.addAttribute("schoolList",schoolList);
            return "userDetail";
        }else {
            List<School> schoolList = schoolService.findAllWithOutPage();
            model.addAttribute("schoolList", schoolList);
            return "userDetail";
        }
    }

    @RequestMapping(value="/addUser",produces = "applications/json;charset=UTF-8")
    @ResponseBody
    public String addUser (User user, HttpServletResponse response) throws Exception{
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

    @RequestMapping(value="/updateUser",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUser(User user, HttpServletResponse response){
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

    @RequestMapping(value="/deleteUser",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteUser(User user, HttpServletResponse response){
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
}
