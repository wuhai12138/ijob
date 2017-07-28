package controller;

import com.alibaba.fastjson.JSON;
import model.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.StudentService;
import util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/7/19.
 */
@Controller
public class StudentPCController extends BaseController{
//    private static final Logger logger = LoggerFactory.getLogger(StudentPCController.class);

    @RequestMapping(value="/studentList")
    public String studentList(Model model, PageParam pageParam){
        Map<String,Object> map = new ReturnMap().getMap(pageParam);
        List<Student> list = studentService.findAll(map);
        model.addAttribute("studentList",list);
        int total = studentService.countAll(map);
        pageParam.setTotal(total);
        model.addAttribute("param",pageParam);
//        logger.info("日志打印");
        return "studentList";
    }

    @RequestMapping(value="/studentDetail",produces = "text/html;charset=UTF-8")
    public String studentDetail(Model model, Student student){
        if(student.getTid() != null){
            Student updateStudent = studentService.findOneById(student);
            model.addAttribute("updateStudent",updateStudent);
            List<School> schoolList = schoolService.findAllWithOutPage();
            model.addAttribute("schoolList",schoolList);
            return "studentDetail";
        }else {
            List<School> schoolList = schoolService.findAllWithOutPage();
            model.addAttribute("schoolList",schoolList);
            return "studentDetail";
        }
    }

    @RequestMapping(value="/studentPersonalFile",produces = "text/html;charset=UTF-8")
    public String studentPersonalFile(Model model, Student student){
        if(student.getTid() != null){
            Student updateStudent = studentService.findOneById(student);
            model.addAttribute("updateStudent",updateStudent);
            return "studentPersonalFile";
        }else {
            return "studentPersonalFile";
        }
    }

    @RequestMapping(value="/addStudent")
    @ResponseBody
    public String addStudent(Student student, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            Student checkStudent = studentService.findOneByCode(student);
            if(checkStudent!=null){
                jsonString.setStatus(1002);
                jsonString.setInfo(StatusCode.CODE1002);
                return JSON.toJSONString(jsonString);
            }else{
                String link = "www.shminde.com:9090/smly/resume/index.php?u="+student.getCode();
                student.setLink(link);
                studentService.addStudent(student);
                //环信新增
                String url = "https://a1.easemob.com/ijob-dh/ijob/users";
                String nickname = student.getNick()+"("+student.getName()+")";
                IMUser imUser = new IMUser(student.getCode(),student.getPassword(),nickname);
                int code = HttpUtil.postJson(url,imUser);
//                String res = HttpUtil.post(url,imUser);
                if(code==200){
                    int codes = HttpUtil.scorePost(student.getCode(),"注册报名所得");
                    jsonString.setStatus(200);
                    jsonString.setInfo(StatusCode.CODE200);
                    return JSON.toJSONString(jsonString);
                }else{
                    jsonString.setStatus(1004);
                    return JSON.toJSONString(jsonString);
                }
            }
        }catch (Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/updateStudent")
    @ResponseBody
    public String updateStudent(Student student, HttpServletResponse response){
        JsonString jsonString = new JsonString();
        try{
            studentService.updateStudent(student);
            jsonString.setStatus(200);
            jsonString.setInfo(StatusCode.CODE200);
            return JSON.toJSONString(jsonString);
        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/deleteStudent")
    @ResponseBody
    public String deleteStudent(Student student, HttpServletRequest request){
        JsonString jsonString = new JsonString();
        try{
            Student checkStudent = studentService.findOneById(student);
            studentService.deleteStudent(student);
//            //环信删除
            String token = request.getSession().getAttribute("token").toString();
            String url = "https://a1.easemob.com/ijob-dh/ijob/users/"+checkStudent.getCode();
//            IMUser imUser = new IMUser(student.getCode(),student.getPassword(),student.getNick());
            int code = HttpUtil.deleteJson(url,token);
//                String res = HttpUtil.post(url,imUser);
            if(code==200){
                jsonString.setStatus(200);
                jsonString.setInfo(StatusCode.CODE200);
                return JSON.toJSONString(jsonString);
            }else{
                jsonString.setStatus(1004);
                return JSON.toJSONString(jsonString);
            }

        }catch(Exception e){
            jsonString.setStatus(500);
            jsonString.setInfo(e.getMessage());
            return JSON.toJSONString(jsonString);
        }
    }

    @RequestMapping(value="/uploadExcel",produces = "text/html;charset=UTF-8")
    public String uploadExcel(){
        return "uploadExcel";
    }

    @RequestMapping(value="/saveExcel")
    @ResponseBody
    public String saveExcel(Model model,HttpServletRequest request,
                            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
//        String path = StatusCode.WINDOWSPATH;
        String fileName = file.getOriginalFilename();
        fileName=System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String s1 = StatusCode.WINDOWSPATH+"\\"+fileName;
        String s1 = path+"/"+fileName;

//        String aa=path+"/"+fileName;
//        String s1 = aa.replaceAll("\\\\","/");
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read(s1);
		/*
		 * 把要导入的数据循环
		 * */
        List<Student> ll=new ArrayList<Student>();
        List<IMUser> imUserList = new ArrayList<>();
        if (list != null){
            for (int i = 0; i < list.size(); i++){
                System.out.print("第" + (i) + "行");
                List<String> cellList = list.get(i);
                if(i!=0){
                    Student student=new Student();
                    student.setCode(cellList.get(0));//登录名
                    student.setName(cellList.get(1));//姓名
                    student.setNick(cellList.get(2));//昵称
                    student.setEmail(cellList.get(3));//Email
                    student.setPhone(cellList.get(4));//电话
                    student.setIntroduction(cellList.get(5));//简介
                    String link = "www.shminde.com:9090/smly/resume/index.php?u="+cellList.get(0);
                    student.setLink(link);//链接
                    student.setPassword(cellList.get(6));//密码
                    student.setStatus(cellList.get(7));//状态
                    student.setSchoolId(cellList.get(8));//学院
                    Student checkDu = studentService.findOneByCode(student);
                    School checkName = new School();
                    checkName.setName(student.getSchoolId());
                    School temp = schoolService.checkOneByName(checkName);
                    student.setSchoolId(String.valueOf(temp.getTid()));
                    if(checkDu==null){
                        studentService.addStudent(student);
                        String nickname = student.getNick()+"("+student.getName()+")";
                        IMUser imUser = new IMUser(student.getCode(),student.getPassword(),nickname);
                        imUserList.add(imUser);
                    }else{
                        student.setTid(checkDu.getTid());
                        studentService.updateStudent(student);
                    }
//                    ll.add(student);
                }
            }
//            studentService.addExcel(ll);
        }
        String token = request.getSession().getAttribute("token").toString();
        String url = "https://a1.easemob.com/ijob-dh/ijob/users";
        int code = HttpUtil.addExcelUser(url,token,imUserList);
        new ImageUpload().deleteFile(request,fileName);
        return "success";
    }

    @RequestMapping(value="/downloadDemo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String downloadDemo(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String fileName = "downloadDemo.xls".toString(); // 文件的默认保存名
        String path = StatusCode.LINUXPATH+"/"+fileName;
        // 读到流中
        InputStream inStream = new FileInputStream(path);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment;filename=downloadDemo.xls");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;

        while ((len = inStream.read(b)) > 0){
            response.getOutputStream().write(b, 0, len);
        }
        inStream.close();
        return null;
    }

    @RequestMapping(value="/chatHistory")
//    @ResponseBody
    public String chatHistory(Student student, HttpServletRequest request,String times,
                              Model model,@RequestParam(defaultValue = "1")int page){
        JsonString jsonString = new JsonString();
        String url;
        List chatHisList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyyMMddHH");
        String   now   =   formatter.format(calendar.getTime());
//        String limits = "500";
//        try{
            //环信获取聊天记录
            String token = request.getSession().getAttribute("token").toString();
            if("".equals(times)){
                url = "https://a1.easemob.com/ijob-dh/ijob/chatmessages/"+now;
            }else {
                times = times.replaceAll("-","");
                url = "https://a1.easemob.com/ijob-dh/ijob/chatmessages/"+times;
            }
            String downloadURL = HttpUtil.chatHis(url,token);
            model.addAttribute("times",times);
            model.addAttribute("downloadURL",downloadURL);
            return "chatHis";

//            if(chatHisList!=null){
//                jsonString.setStatus(200);
//                jsonString.setInfo(StatusCode.CODE200);
//                return JSON.toJSONString(jsonString);
//            }else{
//                jsonString.setStatus(1004);
//                return JSON.toJSONString(jsonString);
//            }

//        }catch(Exception e){
//            jsonString.setStatus(500);
//            jsonString.setInfo(e.getMessage());
//            return JSON.toJSONString(jsonString);
//        }
    }


    @RequestMapping(value="/saveExcel111")
    @ResponseBody
    public String saveExcel111(Model model,HttpServletRequest request,
                            @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("upload");
//        String path = StatusCode.WINDOWSPATH;
        String fileName = file.getOriginalFilename();
        fileName=System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String s1 = StatusCode.WINDOWSPATH+"\\"+fileName;
        String s1 = path+"/"+fileName;

//        String aa=path+"/"+fileName;
//        String s1 = aa.replaceAll("\\\\","/");
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read(s1);
		/*
		 * 把要导入的数据循环
		 * */
//        List<Student> ll=new ArrayList<Student>();
        List<IMUser> imUserList = new ArrayList<>();
        if (list != null){
            for (int i = 0; i < list.size(); i++){
                System.out.print("第" + (i) + "行");
                List<String> cellList = list.get(i);
                if(i!=0){
                    Student student=new Student();
                    student.setCode(cellList.get(0));//登录名
                    student.setName(cellList.get(1));//姓名
                    student.setNick(cellList.get(2));//昵称
//                    student.setEmail(cellList.get(3));//Email
//                    student.setPhone(cellList.get(4));//电话
//                    student.setIntroduction(cellList.get(5));//简介
//                    String link = "www.shminde.com:9090/smly/resume/index.php?u="+cellList.get(0);
//                    student.setLink(link);//链接
                    student.setPassword(cellList.get(3));//密码
//                    student.setStatus(cellList.get(7));//状态
//                    student.setSchoolId(cellList.get(8));//学院
//                    Student checkDu = studentService.findOneByCode(student);
//                    School checkName = new School();
//                    checkName.setName(student.getSchoolId());
//                    School temp = schoolService.checkOneByName(checkName);
//                    student.setSchoolId(String.valueOf(temp.getTid()));
//                    if(checkDu==null){
//                        studentService.addStudent(student);
                        String nickname = student.getNick()+"("+student.getName()+")";
                        IMUser imUser = new IMUser(student.getCode(),student.getPassword(),nickname);
                        imUserList.add(imUser);
//                    }else{
//                        student.setTid(checkDu.getTid());
//                        studentService.updateStudent(student);
//                    }
//                    ll.add(student);
                }
            }
//            studentService.addExcel(ll);
        }
        String token = request.getSession().getAttribute("token").toString();
        String url = "https://a1.easemob.com/ijob-dh/ijob/users";
//        int temp = 0;
//        for(int j = 20;j<imUserList.size();j=j+20){
            int code = HttpUtil.addExcelUser(url,token,imUserList);
//            temp = j;
//            Thread thread = new Thread();
//            thread.sleep(1000);
//        }
//        int code = HttpUtil.addExcelUser(url,token,imUserList);
//        new ImageUpload().deleteFile(request,fileName);
        return "success";
    }

//    public static void main(String[] arg){
//        for(int i=0;i<100;i=i+20){
//            System.out.println(i);
//        }
//    }
}
