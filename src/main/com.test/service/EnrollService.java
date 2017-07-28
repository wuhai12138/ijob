package service;

import model.Enroll;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public interface EnrollService {
    public List<Enroll> findAll(int page);
    public List<Enroll> findAllByInfoId(Enroll enroll);
    public Enroll findOneById(Enroll enroll);
    public Enroll checkOne(Enroll enroll);
    public void addEnroll(Enroll enroll);
    public void updateEnroll(Enroll enroll);
    public void deleteEnroll(Enroll enroll);
    public int countByInfoId(int id);
}
