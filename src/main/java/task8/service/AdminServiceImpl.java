package task8.service;

import task8.dao.InMemoryUserDao;
import task8.dao.UserDao;
import task8.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private static AdminServiceImpl instance;


    private AdminServiceImpl() {
    }

    public static AdminServiceImpl getInstance() {
        if (instance == null) {
            instance = new AdminServiceImpl();
        }
        return instance;
    }

    private UserDao userDao = new InMemoryUserDao();

    @Override
    public void create(User user) throws SQLException {
        userDao.create(user);
        userDao.deleteRole(user);
        userDao.createRole(user);
    }

    @Override
    public User read(int id) throws SQLException {
        ResultSet resultSet = userDao.read(id);
        String name;
        String password;
        String login;
        String dob;
        ArrayList<String> role = new ArrayList<>();
        User user;
        resultSet.next();
        name = resultSet.getString("name");
        password = resultSet.getString("password");
        login = resultSet.getString("login");
        dob = resultSet.getString("dob");
        ResultSet resultSetRoles = userDao.findUserRole(id);
        while (resultSetRoles.next()) {
            role.add(resultSetRoles.getString("role_name"));
        }
        user = new User(id, name, password, role, dob, login);
        userDao.read(user.getId());


        return user;
    }

    @Override
    public void update(User user) throws SQLException{
        userDao.update(user);
        userDao.deleteRole(user);
        userDao.createRole(user);

    }

    @Override
    public void delete(int id) {
        try {
            userDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        ResultSet resultSet = userDao.findAll();
        int id;
        String name;
        String password;
        String login;
        String dob;
        ArrayList<User> users = new ArrayList<>();
        User user;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            password = resultSet.getString("password");
            login = resultSet.getString("login");
            dob = resultSet.getString("dob");
            ArrayList<String> role = new ArrayList<>();
            ResultSet resultSetRoles = userDao.findUserRole(id);
            while (resultSetRoles.next()) {
                role.add(resultSetRoles.getString("role_name"));
            }
            user = new User(id, name, password, role, dob, login);
            users.add(user);

        }
        return users;
    }

    @Override
    public User checkLoginAndPassword(String login, String password) throws SQLException {
        ResultSet resultSet = userDao.findByLoginAndPassword(login, password);
        User user = null;
        if(resultSet != null){
            while (resultSet.next()){
                int id;
                String name;
                String dob;
                ArrayList<String> role = new ArrayList<>();
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                dob = resultSet.getString("dob");
//                role.add("root");
                                ResultSet resultSetRoles = userDao.findUserRole(id);
                while (resultSetRoles.next()) {
                    role.add(resultSetRoles.getString("role_name"));
                }
                user = new User(id, name, password, role, dob, login);
            }
        }
    return user;
    }


    @Override
    public Integer readMaxId() throws SQLException {
        ResultSet resultSet = userDao.readMaxId();
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public List<String> readUserRole(int id) throws SQLException {
        ResultSet resultSet = userDao.findUserRole(id);
        resultSet.next();
        ResultSet numberOfRoles = userDao.readNumberOfRoles();
        numberOfRoles.next();
        ArrayList<String> userRole = new ArrayList<>();
        for (int i = 1; i <= numberOfRoles.getInt(1); i++) {
            if (resultSet.getString(i) != null) {
                userRole.add(resultSet.getString(i));
            }
        }
        return userRole;
    }

}
