package com.thta.task.service;

import java.util.List;

import com.thta.task.model.BsUser;

public interface BsUserService {

	int createBsUser(BsUser user);

	int updateBsUser(BsUser user);

	List<BsUser> getAllBsUser();

	int deleteBsUser(int userId);

	boolean checkUserEmail(String email);

	boolean checkUserByUserId(int userId);

	boolean checkCreateUser(BsUser bsUser);

	boolean checkUpdateUser(BsUser bsUser);

	int getUserIdByUserEmail(String email);
}