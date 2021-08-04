package dao;

import dao.data.UserStorage;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InMemoryUserDao implements UserDao {

    private UserStorage userStorage = UserStorage.getInstance();
    private Connection connection = userStorage.getConnection();

    @Override
    public void create(User user) throws SQLException {
    String request = "INSERT INTO users(login, password, name, dob)\n" +
            "VALUES(?, ?, ?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getDob());
        preparedStatement.executeUpdate();

    }

    @Override
    public ResultSet read(int id) throws SQLException {
        String request = "SELECT * FROM users where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();

    }

    @Override
    public void update(User user) throws SQLException{
        String request = "UPDATE users SET login = ?, password = ?, name = ?, dob = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getDob());
        preparedStatement.setInt(5, user.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(int id) throws SQLException {
        String requestDeleteFromStagingTable = "delete from users_roles where user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(requestDeleteFromStagingTable);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        String requestDelete = "delete from users where id = ?";
        preparedStatement = connection.prepareStatement(requestDelete);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public ResultSet findAll() throws SQLException {
        String request = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        return preparedStatement.executeQuery();
    }

    @Override
    public ResultSet findByLogin(String login) throws SQLException {
        String request = "SELECT * FROM users where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, login);
        return preparedStatement.executeQuery();
    }

    @Override
    public ResultSet findUserRole(int id) throws SQLException {
        String request = "select role_name from roles\n" +
                "left join users_roles ur on roles.id = ur.role_id\n" +
                "where ur.user_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();

    }

    @Override
    public ResultSet findByLoginAndPassword(String login, String password) throws SQLException {
        String request = "select * from users\n" +
                "where login = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);

        return preparedStatement.executeQuery();
    }

    @Override
    public ResultSet readNumberOfRoles() throws SQLException {
        String request = "select count(id) from roles";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        return preparedStatement.executeQuery();
    }

    @Override
    public ResultSet readMaxId() throws SQLException {
        String request = "select max(id) from users";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        return preparedStatement.executeQuery();
    }

    @Override
    public void createRole(User user) throws SQLException {

        String addRole = "INSERT INTO users_roles(user_id, role_id)\n"+
                "VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addRole);
        for (int i = 0; i < user.getRole().size(); i++){

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, Integer.parseInt(user.getRole().get(i)));
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void deleteRole(User user) throws SQLException {
        String deleteRole = "DELETE FROM users_roles WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteRole);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }
}
