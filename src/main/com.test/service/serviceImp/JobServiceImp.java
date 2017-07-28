package service.serviceImp;

import DAO.JobDao;
import model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.JobService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("jobService")
public class JobServiceImp implements JobService {

    @Autowired
    JobDao jobDao;

    public List<Job> findAll(Map<String,Object> map){
        return jobDao.findAll(map);
    };
    public List<Job> findAllWithType(Map<String,Object> map){
        return jobDao.findAllWithType(map);
    };
    public List<Job> findAllWithApply(){
        return jobDao.findAllWithApply();
    };
    public int countAll(Map<String,Object> map){
        return jobDao.countAll(map);
    };
    public List<Job> findAllByStudentId(Map<String,Object> map){
        return jobDao.findAllByStudentId(map);
    };
    public List<Job> findAllByPageAndType(int page,String type){
        return jobDao.findAllByPageAndType(page,type);
    };
    public Job findOneById(Job job){
        return jobDao.findOneById(job);
    };
    public void addJob(Job job){
        jobDao.addJob(job);
    };
    public void updateJob(Job job){
        jobDao.updateJob(job);
    };
    public void deleteJob(Job job){
        jobDao.deleteJob(job);
    };
}
