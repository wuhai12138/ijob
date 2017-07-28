package DAO;

import model.Job;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/24.
 */
@Repository
public interface JobDao {

    public List<Job> findAll(Map<String,Object> map);
    public List<Job> findAllWithType(Map<String,Object> map);
    public List<Job> findAllWithApply();
    public int countAll(Map<String,Object> map);
    public Job findOneById(Job job);
    public List<Job> findAllByStudentId(Map<String,Object> map);
    public List<Job> findAllByPageAndType(@Param("page") int page,@Param("type") String type);
    public void addJob(Job job);
    public void updateJob(Job job);
    public void deleteJob(Job job);

}
