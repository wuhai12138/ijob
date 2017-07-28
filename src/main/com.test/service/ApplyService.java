package service;

import model.Apply;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
public interface ApplyService {
    public List<Apply> findAll(Map<String,Object> map);
    public List<Apply> findStudentByJobId(Apply apply);
    public Apply findOneById(Apply apply);
    public Apply checkDuplicate(Apply apply);
    public void addApply(Apply apply);
    public void updateApply(Apply apply);
    public void deleteApply(Apply apply);
    public int countByJobId(String jobId);
}
