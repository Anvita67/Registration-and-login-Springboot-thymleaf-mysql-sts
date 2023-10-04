package com.app.LoginDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.LoginDemo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User , Integer> {

    User  findByUsername(String username);

}
