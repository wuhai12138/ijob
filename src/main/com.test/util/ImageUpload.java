package util;

import model.StatusCode;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/10.
 */
public class ImageUpload {
    public final String ImgUpload(MultipartFile file, HttpServletRequest request) throws IOException{
//        String path = request.getSession().getServletContext().getRealPath("upload");
         String path = StatusCode.LINUXPATH;
        String fileName = file.getOriginalFilename();
//            info.setImg(fileName);
        if(!"".equals(fileName)){
            fileName=System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
//	        String fileName = new Date().getTime()+".jpg";
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

        }
        return fileName;
    }

    public final void deleteFile(HttpServletRequest request,String path){
        File file = new File(request.getSession().getServletContext().getRealPath("upload")+"/"+path);
//        File file = new File(StatusCode.WINDOWSPATH+"\\"+path);
        if(file.exists()){
            file.delete();
        }
    }
}
