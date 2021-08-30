package task8.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import task8.domain.Role;
import task8.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Mapper
public interface UserDao {

    void create(User user) throws SQLException;
    User read(int id) throws SQLException;
    void update(User user) throws SQLException;
    void delete(int id) throws SQLException;
    List<User> findAll() throws SQLException;
    User findByLogin(String login);
    ResultSet findUserRole(int id) throws SQLException;
    User findByLoginAndPassword(String login, String password) throws SQLException;
    ResultSet readNumberOfRoles() throws SQLException;
    Integer readMaxId() throws  SQLException;
    void createRole(Integer user_id, List<Role> roles) throws SQLException;
    void deleteRole(int id) throws SQLException;
}
