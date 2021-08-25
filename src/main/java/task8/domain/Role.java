package task8.domain;

import java.util.Objects;

public class Role {
    private Integer id;
    private String name;

    public Role() {
    }

    public Role(int id, String name){
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Role(int id) {
        this.id = id;
        if (id == 1) {
            this.name = "root";
        }
        if (id == 2) {
            this.name = "user";
        }
        if (id == 3) {
            this.name = "manager";
        }
        if (id == 4) {
            this.name = "developer";
        }
        if (id == 5) {
            this.name = "seo";
        }
    }

    public Role(String name) {
        this.name = name;
        if (name.equals("root")) {
            this.id = 1;
        }
        if (name.equals("user")) {
            this.id = 2;
        }
        if (name.equals("manager")) {
            this.id = 3;
        }
        if (name.equals("developer")) {
            this.id = 4;
        }
        if (name.equals("seo")) {
            this.id = 5;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

