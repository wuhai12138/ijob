package service.serviceImp;

import DAO.EnrollDao;
import model.Enroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.EnrollService;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("enrollService")
public class EnrollServiceImp implements EnrollService {

    @Autowired
    EnrollDao enrollDao;

    public List<Enroll> findAll(int page){
        return enrollDao.findAll(page);
    };
    public List<Enroll> findAllByInfoId(Enroll enroll){return enrollDao.findAllByInfoId(enroll);}
    public Enroll findOneById(Enroll enroll){
        return enrollDao.findOneById(enroll);
    };
    public Enroll checkOne(Enroll enroll){
        return enrollDao.checkOne(enroll);
    };
    public void addEnroll(Enroll enroll){
        enrollDao.addEnroll(enroll);
    };
    public void updateEnroll(Enroll enroll){
        enrollDao.updateEnroll(enroll);
    };
    public void deleteEnroll(Enroll enroll){
        enrollDao.deleteEnroll(enroll);
    };
    public int countByInfoId(int id){
        return enrollDao.countByInfoId(id);
    };
}
