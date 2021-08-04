package dao.data;

import domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;



public class UserStorage {
    public static List<User> users = new ArrayList<>(Arrays.asList(
            new User(1, "root", "1", Collections.singletonList("root"),"1990-01-01", "root@root.com"),
            new User(2, "молд", "1", Collections.singletonList("user"), "1991-02-02", "user@user.com"),
            new User(3, "user2", "2", Collections.singletonList("user"), "1993-03-03", "user2@user.com"),
            new User(4, "user3", "3", Collections.singletonList("user"), "1994-04-04", "user3@user.com")
    ));

    private static UserStorage instance = new UserStorage();
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5433/authorization_role_MM";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    private UserStorage(){
    }

    public static UserStorage getInstance(){
        return instance;
    }

    public Connection getConnection() {

        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }
}
