package service.serviceImp;

import DAO.ApplyDao;
import model.Apply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ApplyService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("applyService")
public class ApplyServiceImp implements ApplyService {

    @Autowired
    ApplyDao applyDao;

    public List<Apply> findAll(Map<String,Object> map){
        return applyDao.findAll(map);
    };
    public List<Apply> findStudentByJobId(Apply apply){return applyDao.findStudentByJobId(apply);};
    public Apply findOneById(Apply apply){
        return applyDao.findOneById(apply);
    };
    public Apply checkDuplicate(Apply apply){
        return applyDao.checkDuplicate(apply);
    };
    public void addApply(Apply apply){
        applyDao.addApply(apply);
    };
    public void updateApply(Apply apply){
        applyDao.updateApply(apply);
    };
    public void deleteApply(Apply apply){
        applyDao.deleteApply(apply);
    };
    public int countByJobId(String jobId){
        return applyDao.countByJobId(jobId);
    };
}
