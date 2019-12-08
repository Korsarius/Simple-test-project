package ua.nure.entity;

import java.io.Serializable;

/**
 * A role of user.
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 745937615147622105L;
    private int id;
    private String name;

    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

}
