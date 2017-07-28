package service;

import model.Notice;

import java.util.List;

/**
 * Created by jygj_7500 on 2017/7/26.
 */
public interface NoticeService {
    public List<Notice> getNoticeAPP();

    public List<Notice> getNotice(int page);

    public Notice getNoticeById(int id);

    public void addNotice(Notice notice);

    public void updateNotice(Notice notice);

    public void  deleteNotice(Notice notice);
}
