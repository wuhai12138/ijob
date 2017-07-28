package DAO;

import model.Enroll;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
@Repository
public interface EnrollDao {

    public List<Enroll> findAll(int page);
    public List<Enroll> findAllByInfoId(Enroll enroll);
    public Enroll findOneById(Enroll enroll);
    public Enroll checkOne(Enroll enroll);
    public void addEnroll(Enroll enroll);
    public void updateEnroll(Enroll enroll);
    public void deleteEnroll(Enroll enroll);
    public int countByInfoId(int id);

}
