package com.nero.springsecurity.jpa;

import com.nero.springsecurity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
