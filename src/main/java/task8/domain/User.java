package task8.domain;

import java.util.List;

public class User {
    private Integer id;
    private String login;
    private String password;
    private List<Role> roles;
    private String dob;
    private String email;


    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String login, String password, List<Role> roles, String dob, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.dob = dob;
        this.email = email;
    }

    public User( String login, String password, List<Role> roles, String dob, String email) {
        this.login = login;
        this.password = password;
        this.roles = roles;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
