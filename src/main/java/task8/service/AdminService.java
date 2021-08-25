package task8.service;

import task8.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    void create(User user) throws SQLException;

    User read(int id) throws SQLException;

    void update(User user) throws SQLException;

    void delete(int id) throws SQLException;

    List<User> findAll() throws SQLException;

    User checkLoginAndPassword(String login, String password) throws SQLException;

    Integer readMaxId() throws SQLException;

}
