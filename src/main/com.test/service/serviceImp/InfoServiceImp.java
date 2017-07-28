package service.serviceImp;

import DAO.InfoDao;
import model.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.InfoService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("infoService")
public class InfoServiceImp implements InfoService {

    @Autowired
    InfoDao infoDao;

    public List<Info> findAll(int page){
        return infoDao.findAll(page);
    };
    public List<Info> findAllByStudentId(String studentId,int page){
        return infoDao.findAllByStudentId(studentId,page);
    };
    public List<Info> findAllByPageAndType(Map<String,Object> map){
        return infoDao.findAllByPageAndType(map);
    };
    public List<Info> findAllWithEnroll(){
        return infoDao.findAllWithEnroll();
    };
    public List<Info> findAllWithType(int type){
        return infoDao.findAllWithType(type);
    };
    public int countAllByType(Map<String,Object> map){
        return infoDao.countAllByType(map);
    };
    public Info findOneById(Info info){
        return infoDao.findOneById(info);
    };
    public void addInfo(Info info){
        infoDao.addInfo(info);
    };
    public void updateInfo(Info info){
        infoDao.updateInfo(info);
    };
    public void deleteInfo(Info info){
        infoDao.deleteInfo(info);
    };
    public void changeInfoStatus(Info info){
        infoDao.changeInfoStatus(info);
    };
}
