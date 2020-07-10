package com.nero.springsecurity.po;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String password;
    @OneToMany(targetEntity = Role.class)
    //指定Person与Address的关联表
    @JoinTable(name = "user_role", //指定关联表名
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id"), //指定关联表中用于关联Person表的外键
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id", unique = true)  //指定关联表中用于关联Address表的外键
            })
    //指定级联操作策略，此处表示对Person实体的所有持久化操作都会级联到它关联的Address实体
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
