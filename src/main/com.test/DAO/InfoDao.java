package DAO;

import model.Info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/24.
 */
@Repository
public interface InfoDao {

    public List<Info> findAll(@Param("page") int page);
    public List<Info> findAllByStudentId(@Param("studentId") String studentId,@Param("page") int page);
    public List<Info> findAllByPageAndType(Map<String,Object> map);
    public List<Info> findAllWithEnroll();
    public List<Info> findAllWithType(@Param("type")int type);
    public int countAllByType(Map<String,Object> map);
    public Info findOneById(Info info);
    public void addInfo(Info info);
    public void updateInfo(Info info);
    public void deleteInfo(Info info);
    public void changeInfoStatus(Info info);

}
