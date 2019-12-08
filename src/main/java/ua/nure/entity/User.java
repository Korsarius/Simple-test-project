package ua.nure.entity;

import java.io.Serializable;

/**
 * A user entity.
 * Contains user's id, login, password and avatar.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 745937615147622105L;
    private Long id;
    private String login;
    private String password;
    private String avatar;
    private Role role;

    public User() {
    }

    public User(Long id, String login, String password, String avatar, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.role = role;
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String login() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String avatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role role() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
