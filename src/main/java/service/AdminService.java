package service;

import domain.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    void create(User user) throws SQLException;

    User read(int id) throws SQLException;

    void update(User user) throws SQLException;

    void delete(int id);

    List<User> findAll() throws SQLException;

    User checkLoginAndPassword(String login, String password) throws SQLException;

    Integer readMaxId() throws SQLException;

    List<String> readUserRole(int id) throws SQLException;

}
