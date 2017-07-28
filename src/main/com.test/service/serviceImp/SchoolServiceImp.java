package service.serviceImp;

import DAO.SchoolDao;
import model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SchoolService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("schoolService")
public class SchoolServiceImp implements SchoolService {

    @Autowired
    SchoolDao schoolDao;

    public List<School> findAll(Map<String,Object> map){
        return schoolDao.findAll(map);
    };
    public Integer countAll(Map<String,Object> map){
        return schoolDao.countAll(map);
    };
    public List<School> findAllWithOutPage(){
        return schoolDao.findAllWithOutPage();
    };
    public School findOneById(School school){
        return schoolDao.findOneById(school);
    };
    public School checkOneByName(School school){
        return schoolDao.checkOneByName(school);
    };
    public void addSchool(School school){
        schoolDao.addSchool(school);
    };
    public void updateSchool(School school){
        schoolDao.updateSchool(school);
    };
    public void deleteSchool(School school){
        schoolDao.deleteSchool(school);
    };
}
