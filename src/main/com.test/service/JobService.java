package service;

import model.Job;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
public interface JobService {
    public List<Job> findAll(Map<String,Object> map);
    public List<Job> findAllWithType(Map<String,Object> map);
    public List<Job> findAllWithApply();
    public int countAll(Map<String,Object> map);
    public List<Job> findAllByPageAndType(int page,String type);
    public List<Job> findAllByStudentId(Map<String,Object> map);
    public Job findOneById(Job job);
    public void addJob(Job job);
    public void updateJob(Job job);
    public void deleteJob(Job job);
}
