package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.HttpUtil;
import util.ReturnMap;
import util.jsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2016/8/23.
 */
@Controller
public class GroupController extends BaseController {

    @RequestMapping(value="/groupList")
    public String groupList(Model model, HttpServletRequest request){
        //环信群组列表
        String token = request.getSession().getAttribute("token").toString();
        String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups";
//            IMUser imUser = new IMUser(student.getCode(),student.getPassword(),student.getNick());
        String res = HttpUtil.chatgroups(url,token);
        Map<String,Object> map = jsonUtil.json2Map(res);
        List<ChatGroup> chatGroupList = jsonUtil.json2List(map.get("data").toString(),ChatGroup.class);
        Collections.reverse(chatGroupList);
        model.addAttribute("chatGroupList",chatGroupList);
        return "groupList";
    }

    @RequestMapping(value="/groupDetail")
    public String groupDetail(Model model, HttpServletRequest request, AddGroup addGroup,ChatGroup chatGroup){
        //环信群组详情
        if(chatGroup.getGroupid()!=null){
            //更新群组
            String token = request.getSession().getAttribute("token").toString();
            String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups/"+chatGroup.getGroupid();
            String res = HttpUtil.getOneGroup(url,token);
            Map<String,Object> map = jsonUtil.json2Map(res);
            String test = map.get("data").toString();
            List<AddGroup> addGroupList = jsonUtil.json2List(map.get("data").toString(),AddGroup.class);
            model.addAttribute("updateGroup",addGroupList.get(0));
        }else{
            //新增群组页面

        }
        return "groupDetail";
    }

    @RequestMapping(value="/addGroup")
    @ResponseBody
    public String addGroup(Model model, HttpServletRequest request, AddGroup addGroup){
        //新建环信群组
        JsonString jsonString = new JsonString();
        String token = request.getSession().getAttribute("token").toString();
        String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups";
        addGroup.setOwner("admin");
        Map<String,Object> map = jsonUtil.Obj2Map(addGroup);
        map.put("public",false);
        int code = HttpUtil.addGroups(url,token,map);
        if (code == 200) {
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }else if(code==1005){
            jsonString.setStatus(1005);
            return JSON.toJSONString(jsonString);
        }else{
            jsonString.setStatus(500);
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateGroup")
    @ResponseBody
    public String updateGroup(Model model, HttpServletRequest request, AddGroup addGroup){
        //更新环信群组
        JsonString jsonString = new JsonString();
        String token = request.getSession().getAttribute("token").toString();
        String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups/"+addGroup.getId();
        Map<String,Object> map = jsonUtil.Obj2Map(addGroup);
        map.remove("id");
        int code = HttpUtil.updateGroups(url,token,map);
        if (code == 200) {
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }else if(code==1005){
            jsonString.setStatus(1005);
            return JSON.toJSONString(jsonString);
        }else{
            jsonString.setStatus(500);
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteGroup")
    @ResponseBody
    public String deleteGroup(Model model, HttpServletRequest request, ChatGroup chatGroup){
        //删除环信群组
        JsonString jsonString = new JsonString();
        String token = request.getSession().getAttribute("token").toString();
        String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups/"+chatGroup.getGroupid();
        int code = HttpUtil.deleteGroup(url,token);
        if (code == 200) {
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }else if(code==1005){
            jsonString.setStatus(1005);
            return JSON.toJSONString(jsonString);
        }else{
            jsonString.setStatus(500);
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/memberList")
    public String memberList(Model model, HttpServletRequest request,String groupid, String flag){
        //环信群组成员

            String token = request.getSession().getAttribute("token").toString();
            String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups/"+groupid+"/users";
            String res = HttpUtil.groupMember(url,token);
            Map<String,Object> map = jsonUtil.json2Map(res);
            List<Student> studentList = jsonUtil.json2List(map.get("data").toString(),Student.class);
            List<String> codeList = new ArrayList<String>();
            for(Student s : studentList){
                codeList.add(s.getMember());
            }
            List<Student> allStudents = studentService.getAll();
            for(Student s : allStudents){
                if(codeList.contains(s.getCode())){
                    s.setCheck(1);
                }
            }
            model.addAttribute("studentList",allStudents);


        model.addAttribute("groupid",groupid);
        model.addAttribute("flag",flag);
        return "memberList";
    }

    @RequestMapping(value="/saveMember")
    @ResponseBody
    public String saveMember(HttpServletRequest request, String member, String groupid){
        //保存成员
        JsonString jsonString = new JsonString();
        String token = request.getSession().getAttribute("token").toString();
        String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups/"+groupid+"/users";
        String[] memberList = member.split(",");
        GroupMember groupMember = new GroupMember(memberList);
        int code = HttpUtil.saveMember(url,token,groupMember);
        if (code == 200) {
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }else if(code==1005){
            jsonString.setStatus(1005);
            return JSON.toJSONString(jsonString);
        }else{
            jsonString.setStatus(500);
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteMember")
    @ResponseBody
    public String deleteMember(HttpServletRequest request, String member, String groupid){
        //保存成员
        JsonString jsonString = new JsonString();
        String token = request.getSession().getAttribute("token").toString();
        String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups/"+groupid+"/users/"+member;
        int code = HttpUtil.deleteMember(url,token);
        if (code == 200) {
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }else if(code==1005){
            jsonString.setStatus(1005);
            return JSON.toJSONString(jsonString);
        }else{
            jsonString.setStatus(500);
            return JSON.toJSONString(jsonString);
        }
    }
}
