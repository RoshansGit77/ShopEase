package com.ecom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.UserDetail;
import com.ecom.repository.UserRepo;
import com.ecom.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired	
	private UserRepo userRepo;
	
	@Override
	public UserDetail saveUser(UserDetail user) {
		UserDetail saveUser = userRepo.save(user);
		return saveUser;
	}

}
