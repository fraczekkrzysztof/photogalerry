package com.fraczekkrzysztof.photogallery.entity;

import javax.persistence.*;

@Entity
@Table(name="authorities",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"username", "authority"}))
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String username;
    @Column
    private String authority;

    public Role() {
    }

    public Role(long id, String username, String authority) {
        this.id = id;
        this.username = username;
        this.authority = authority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
