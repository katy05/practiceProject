package task8.dao;

import task8.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDao {

    void create(User user) throws SQLException;
    ResultSet read(int id) throws SQLException;
    void update(User user) throws SQLException;
    void delete(int id) throws SQLException;
    ResultSet findAll() throws SQLException;
    ResultSet findByLogin(String login) throws SQLException;
    ResultSet findUserRole(int id) throws SQLException;
    ResultSet findByLoginAndPassword(String login, String password) throws SQLException;
    ResultSet readNumberOfRoles() throws SQLException;
    ResultSet readMaxId() throws  SQLException;
    void createRole(User user) throws SQLException;
    void deleteRole(User user) throws SQLException;
}
