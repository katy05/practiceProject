package task8.domain;

import java.util.List;

public class User {
    private Integer id;
    private String login;
    private String password;
    private List<String> role;
    private String dob;
    private String email;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String login, String password, List<String> role, String dob, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.dob = dob;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
