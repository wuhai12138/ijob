package controller;

import com.alibaba.fastjson.JSON;
import freemarker.template.utility.StringUtil;
import model.Info;
import model.JsonString;
import model.Notice;
import model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.NoticeService;
import util.ImageUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by jygj_7500 on 2017/7/26.
 */
@Controller
public class NoticePCController {
    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/noticeList")
    public String getNotice(Model model, int page) {
        try {
            page = (page - 1) * 20;
            List<Notice> list = noticeService.getNoticeAPP();
            model.addAttribute("noticeList", list);
            return "noticeList";
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "error";
        }

    }

    @RequestMapping(value = "/noticeDetail")
    public String noticeDetail(Model model, Notice notice) {
        if (notice.getNoticeId() != null) {
            Notice updateNotice = noticeService.getNoticeById(notice.getNoticeId());
            model.addAttribute("updateNotice", updateNotice);
            return "noticeDetail";
        } else {
            model.addAttribute("groupName", notice.getGroupName());
            return "noticeDetail";
        }
    }

    @RequestMapping(value = "/addNotice")
    public String addNotice(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file,
                            Notice notice, Model model) throws IOException {
        try {
            String fileName = new ImageUpload().ImgUpload(file, request);
            notice.setPicture(fileName);
            notice.setTitle(URLDecoder.decode(notice.getTitle(), "UTF-8"));
            notice.setContent(URLDecoder.decode(notice.getContent(), "UTF-8"));
            notice.setLink(URLDecoder.decode(notice.getLink(), "UTF-8"));
            noticeService.addNotice(notice);
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "error";
        }

    }

    @RequestMapping(value = "/updateNotice")
    public String updateNotice(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file,
                               Notice notice, Model model) throws IOException {
        try {
            String fileName = new ImageUpload().ImgUpload(file, request);
            if(!fileName.isEmpty()){
                notice.setPicture(fileName);
            }
            if (!notice.getTitle().isEmpty()) {
                notice.setTitle(URLDecoder.decode(notice.getTitle(), "UTF-8"));
            }
            if (!notice.getContent().isEmpty()) {
                notice.setContent(URLDecoder.decode(notice.getContent(), "UTF-8"));
            }
            if (!notice.getLink().isEmpty()) {
                notice.setLink(URLDecoder.decode(notice.getLink(), "UTF-8"));
            }
            noticeService.updateNotice(notice);
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "error";
        }
    }

    @RequestMapping(value = "/deleteNotice")
    @ResponseBody
    public String deleteNotice(Notice notice, HttpServletRequest request) {
        JsonString jsonString = new JsonString();
        try {
            Notice deleteNotice = noticeService.getNoticeById(notice.getNoticeId());
            noticeService.deleteNotice(notice);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        } catch (Exception e) {
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }


}
