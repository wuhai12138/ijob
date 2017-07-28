package DAO;

import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
@Repository
public interface UserDao {

    public List<User> findAllUser();
    public User findOneById(User user);
    public User findOneByName(User user);
    public User findOneByNameAndPwd(User user);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);

}
