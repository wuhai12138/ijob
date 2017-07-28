package service.serviceImp;

import DAO.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("UserService")
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;

    public List<User> findAllUser(){
        return userDao.findAllUser();
    };
    public User findOneById(User user){
        return userDao.findOneById(user);
    };
    public User findOneByName(User user){
        return userDao.findOneByName(user);
    };
    public User findOneByNameAndPwd(User user){return userDao.findOneByNameAndPwd(user);}
    public void addUser(User user){
        userDao.addUser(user);
    };
    public void updateUser(User user){
        userDao.updateUser(user);
    };
    public void deleteUser(User user){
        userDao.deleteUser(user);
    };
}
