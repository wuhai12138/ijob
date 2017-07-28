package service;

import model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public interface UserService {
    public List<User> findAllUser();
    public User findOneById(User user);
    public User findOneByName(User user);
    public User findOneByNameAndPwd(User user);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
}
