package service.serviceImp;

import DAO.StudentDao;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StudentService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("studentService")
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentDao studentDao;

    public List<Student> findAll(Map<String,Object> map){
        return studentDao.findAll(map);
    };
    public List<Student> getAll(){
        return studentDao.getAll();
    };
    public int countAll(Map<String,Object> map){
        return studentDao.countAll(map);
    };
    public Student findOneById(Student student){
        return studentDao.findOneById(student);
    };
    public Student findOneByCodeAndPassword(Student student){
        return studentDao.findOneByCodeAndPassword(student);
    };
    public Student findOneByCode(Student student){
        return studentDao.findOneByCode(student);
    };
    public Student fillAllByCode(Student student){return studentDao.fillAllByCode(student);}
    public void addStudent(Student student){
        studentDao.addStudent(student);
    };
    public void updateStudent(Student student){
        studentDao.updateStudent(student);
    };
    public void addExcel(List<Student> list){studentDao.addExcel(list);}
    public void deleteStudent(Student student){
        studentDao.deleteStudent(student);
    };
    public Student checkDuplicate(Student student){
        return studentDao.checkDuplicate(student);
    }
}
