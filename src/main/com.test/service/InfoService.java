package service;

import model.Info;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
public interface InfoService {
    public List<Info> findAll(int page);
    public List<Info> findAllByStudentId(String studentId,int page);
    public List<Info> findAllByPageAndType(Map<String,Object> map);
    public List<Info> findAllWithEnroll();
    public List<Info> findAllWithType(int type);
    public int countAllByType(Map<String,Object> map);
    public Info findOneById(Info info);
    public void addInfo(Info info);
    public void updateInfo(Info info);
    public void deleteInfo(Info info);
    public void changeInfoStatus(Info info);
}
