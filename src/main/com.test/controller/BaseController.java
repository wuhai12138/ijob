package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class BaseController {
    @Autowired
    ApplyService applyService;
    @Autowired
    CompanyService companyService;
    @Autowired
    EnrollService enrollService;
    @Autowired
    InfoService infoService;
    @Autowired
    JobService jobService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;
    @Autowired
    NoticeService noticeService;

}
