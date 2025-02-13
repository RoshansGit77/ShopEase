package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.UserDetail;

public interface UserRepo extends JpaRepository<UserDetail, Integer> {

}
