package com.thta.task.service;

import java.util.List;

import com.thta.task.model.BsUser;

public interface BsUserService {

	List<BsUser> getAllBsUser();

	int createBsUser(BsUser user);

	int updateBsUser(BsUser user);

	int deleteBsUser(BsUser user);

	boolean checkUserEmail(String email);

	int getUserIdByUserEmail(String email);

	boolean checkUserByUserId(int userId);
}