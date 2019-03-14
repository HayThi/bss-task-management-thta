package com.thta.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thta.task.model.BsUser;
import com.thta.task.repository.BsUserRepository;
import com.thta.task.service.BsUserService;

@Service
public class BsUserServiceImpl implements BsUserService {

	@Autowired
	private BsUserRepository bsUserRepository;

	@Override
	public int createBsUser(BsUser user) {
		int result = 0;
		if (!checkUserEmail(user.getUser_email())) {
			result = bsUserRepository.createBsUser(user);
		}

		return result;
	}

	@Override
	public int updateBsUser(BsUser user) {
		return bsUserRepository.updateBsUser(user);
	}

	@Override
	public List<BsUser> getAllBsUser() {
		return bsUserRepository.getAllBsUser();
	}

	@Override
	public int deleteBsUser(int userId) {
		return bsUserRepository.deleteBsUser(userId);
	}

	// To check data: name, email and password are required.
	@Override
	public boolean checkCreateUser(BsUser bsUser) {
		boolean result = false;
		if (bsUser != null) {
			if (bsUser.getUser_name() != null && !bsUser.getUser_name().equals("") && bsUser.getUser_email() != null
					&& !bsUser.getUser_email().equals("") && bsUser.getUser_pwd() != null
					&& !bsUser.getUser_pwd().equals("")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * To check data: email is required. User name or User password is required.
	 */
	@Override
	public boolean checkUpdateUser(BsUser bsUser) {
		boolean result = false;
		if (bsUser != null) {
			if ((bsUser.getUser_name() != null && !bsUser.getUser_name().equals(""))
					|| (bsUser.getUser_pwd() != null && !bsUser.getUser_pwd().equals(""))) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean checkUserEmail(String email) {
		return bsUserRepository.checkUserEmail(email);
	}

	@Override
	public boolean checkUserByUserId(int userId) {
		boolean result = false;
		if (userId != 0) {
			result = bsUserRepository.checkUserByUserId(userId);
		}

		return result;
	}

	@Override
	public int getUserIdByUserEmail(String email) {
		return bsUserRepository.getUserIdByUserEmail(email);
	}

}