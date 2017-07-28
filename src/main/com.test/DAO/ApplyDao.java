package DAO;

import model.Apply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/24.
 */
@Repository
public interface ApplyDao {

    public List<Apply> findAll(Map<String,Object> map);
    public List<Apply> findStudentByJobId(Apply apply);
    public Apply findOneById(Apply apply);
    public Apply checkDuplicate(Apply apply);
    public void addApply(Apply apply);
    public void updateApply(Apply apply);
    public void deleteApply(Apply apply);
    public int countByJobId(String jobId);

}
