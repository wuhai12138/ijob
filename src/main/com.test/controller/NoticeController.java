package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.HttpUtil;
import util.jsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jygj_7500 on 2017/7/26.
 */
@Controller
public class NoticeController extends BaseController{
    @RequestMapping(value = "/noticeListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNoticeApp(String code){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        try {
            List<Notice> list = noticeService.getNoticeAPP(code);
            jsonList.setList(list);
            if(list.size()>0){
                jsonString.setStatus(200);
                jsonString.setInfo(StatusCode.CODE200);
                jsonString.setData(jsonList);
                return JSON.toJSONString(jsonString);
            }else {
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

    //遍历老数据，已停用。
    @RequestMapping(value = "/noticeListAppUpdate",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateNoticeApp(HttpServletRequest request){
        List<String> idList = new ArrayList<String>();
        idList.add("12588744245252");
        idList.add("12588206325761");
        idList.add("12587936841730");
        idList.add("12587375853569");
        idList.add("12587192352770");
        idList.add("12586945937409");
        idList.add("12586723639298");
        idList.add("12586370269185");
        for (int j = 0; j<idList.size(); j++){
            String token = request.getSession().getAttribute("token").toString();
            String url = "https://a1.easemob.com/ijob-dh/ijob/chatgroups/"+idList.get(j)+"/users";
            String res = HttpUtil.groupMember(url,token);
            Map<String,Object> map = jsonUtil.json2Map(res);
            List<Student> studentList = jsonUtil.json2List(map.get("data").toString(),Student.class);
            for(int i=0; i < studentList.size(); i++){
                studentList.get(i).setCode(idList.get(j));
                noticeService.insertStudentCode(studentList.get(i));
            }

        }
        return null;
    }
}
