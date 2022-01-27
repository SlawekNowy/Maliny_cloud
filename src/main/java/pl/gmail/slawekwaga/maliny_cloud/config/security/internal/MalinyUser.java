package pl.gmail.slawekwaga.maliny_cloud.config.security.internal;

import javax.persistence.*;

@Entity
@Table(name = "maliny_users")
public class MalinyUser {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_password_valid")
    private Boolean isPasswordValid;

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsPasswordValid() {
        return isPasswordValid;
    }

    public void setIsPasswordValid(Boolean isPasswordValid) {
        this.isPasswordValid = isPasswordValid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String id) {
        this.username = id;
    }
}