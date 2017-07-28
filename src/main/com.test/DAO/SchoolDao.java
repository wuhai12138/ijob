package DAO;

import model.School;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/24.
 */
@Repository
public interface SchoolDao {

    public List<School> findAll(Map<String,Object> map);
    public Integer countAll(Map<String,Object> map);
    public List<School> findAllWithOutPage();
    public School findOneById(School school);
    public School checkOneByName(School school);
    public void addSchool(School school);
    public void updateSchool(School school);
    public void deleteSchool(School school);

}
