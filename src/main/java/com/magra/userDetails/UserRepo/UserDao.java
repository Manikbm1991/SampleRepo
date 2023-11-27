package com.magra.userDetails.UserRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magra.userDetails.model.UserInfo;

public interface UserDao extends JpaRepository<UserInfo, Long>{

}
