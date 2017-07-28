package service.serviceImp;

import DAO.NoticeDao;
import com.sun.tools.corba.se.idl.constExpr.Not;
import model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.NoticeService;

import java.util.List;

/**
 * Created by jygj_7500 on 2017/7/26.
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    NoticeDao noticeDao;

    public List<Notice> getNotice(int page){
        return noticeDao.getNotice(page);
    }

    public List<Notice> getNoticeAPP(){
        return noticeDao.getNoticeAPP();
    }

    public Notice getNoticeById(int id){
        return  noticeDao.getNoticeById(id);
    }

    public void addNotice(Notice notice){
        noticeDao.addNotice(notice);
    }

    public void updateNotice(Notice notice){
        noticeDao.updateNotice(notice);
    }

    public void deleteNotice(Notice notice){
        noticeDao.deleteNotice(notice);
    }
}
