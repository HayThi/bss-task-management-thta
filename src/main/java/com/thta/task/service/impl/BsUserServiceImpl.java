package com.thta.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thta.task.model.BsUser;
import com.thta.task.repository.BsUserRepository;
import com.thta.task.service.BsUserService;

@Service
public class BsUserServiceImpl implements BsUserService{
	@Autowired
	BsUserRepository bsUserRepository;
	
	@Override
	public List<BsUser> getAllBsUser() {
		return bsUserRepository.getAllBsUser();
	}
	
	@Override
	public int createBsUser(BsUser user) {
		return bsUserRepository.createBsUser(user);
	}
	
	@Override
	public int updateBsUser(BsUser user) {
		return bsUserRepository.updateBsUser(user);	
	}
	
	@Override
	public int deleteBsUser(BsUser user) {
		return bsUserRepository.deleteBsUser(user);
		
	}
	
	@Override
	public boolean checkUserEmail (String email) {
		return bsUserRepository.checkUserEmail(email);
	}
	
	@Override
	public int getUserIdByUserEmail(String email) {
		return bsUserRepository.getUserIdByUserEmail(email);
	}
	
	@Override
	public boolean checkUserByUserId(int userId) {
		return bsUserRepository.checkUserByUserId(userId);
	}
	
}