package DAO;

import model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/24.
 */
@Repository
public interface StudentDao {

    public List<Student> findAll(Map<String,Object> map);
    public List<Student> getAll();
    public int countAll(Map<String,Object> map);
    public Student findOneById(Student student);
    public Student findOneByCodeAndPassword(Student student);
    public Student findOneByCode(Student student);
    public Student fillAllByCode(Student student);
    public void addStudent(Student student);
    public void updateStudent(Student student);
    public void addExcel(List<Student> list);
    public void deleteStudent(Student student);
    public Student checkDuplicate(Student student);

}
