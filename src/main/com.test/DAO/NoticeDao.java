package DAO;

import model.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jygj_7500 on 2017/7/26.
 */
@Repository
public interface NoticeDao {
    public List<Notice> getNoticeAPP();

    public List<Notice> getNotice(int page);

    public Notice getNoticeById(int id);

    public void  addNotice(Notice notice);

    public void updateNotice(Notice notice);

    public void deleteNotice(Notice notice);
}
