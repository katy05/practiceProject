package task8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task8.dao.UserDao;
import task8.domain.Role;
import task8.domain.User;
//import task8.storage.ConnectMyBatis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
//    private SqlSession sqlSession = ConnectMyBatis.getSqlSession();

    @Autowired
    private UserDao userDao;

    @Override
    public void create(User user) throws SQLException {
        userDao.create(user);
        // userDao.deleteRole(user);
        userDao.createRole(userDao.findByLogin(user.getLogin()).getId(), user.getRoles());
    }

    @Override
    public User read(int id) throws SQLException {
        return userDao.read(id);
    }

    @Override
    public void update(User user) throws SQLException {
        userDao.update(user);
        userDao.deleteRole(user.getId());
        userDao.createRole(userDao.findByLogin(user.getLogin()).getId(), user.getRoles());
    }

    @Override
    public void delete(int id) throws SQLException {
        userDao.deleteRole(id);
        userDao.delete(id);

    }

    @Override
    public List<User> findAll() throws SQLException {
        return userDao.findAll();
    }

    @Override
    public User checkLoginAndPassword(String login, String password) throws SQLException {
        return userDao.findByLoginAndPassword(login, password);
    }


    @Override
    public Integer readMaxId() throws SQLException {
        return userDao.readMaxId();
    }


}
