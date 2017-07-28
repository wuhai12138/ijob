package controller;

import com.alibaba.fastjson.JSON;
import model.JsonList;
import model.JsonString;
import model.Notice;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jygj_7500 on 2017/7/26.
 */
@Controller
public class NoticeController extends BaseController{
    @RequestMapping(value = "/noticeListApp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNoticeApp(int page){
        JsonString jsonString = new JsonString();
        JsonList jsonList = new JsonList();
        try {
            List<Notice> list = noticeService.getNoticeAPP();
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
}
