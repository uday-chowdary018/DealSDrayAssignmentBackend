package com.uday.kiran.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uday.kiran.emp.entitys.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByuserName(String userName);
}
