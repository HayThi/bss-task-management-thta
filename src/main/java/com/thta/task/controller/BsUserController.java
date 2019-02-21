package com.thta.task.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thta.task.model.BsMessage;
import com.thta.task.model.BsUser;
import com.thta.task.service.BsUserService;

/**
 * This is the user controller.
 * */
@RestController
public class BsUserController {
	
	private Logger logger = LogManager.getLogger(BsUserController.class);	

	@Autowired
	BsUserService bsUserService;

	//To show all user.
	@RequestMapping("/getAllBsUser")
	public List<BsUser> getAllBsUser() {
		logger.info(this.getClass().getSimpleName() + ": getAllBsUser");
		return bsUserService.getAllBsUser();
	}	

	// To create user.
	@RequestMapping(value = "/createBsUser", method = RequestMethod.POST)
	public BsMessage createBsUser(@RequestBody BsUser bsUser) {
		logger.info(this.getClass().getSimpleName() + ": createBsUser");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Create user Unsuccessfully.");

		if (checkCreateUser(bsUser)) {
			if (bsUserService.createBsUser(bsUser) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Create user successfully.");
			}
		}
		return bsMsg;
	}

	// To check data: name, email and password are required.
	private boolean checkCreateUser(BsUser bsUser) {
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
	 * To update the user data.
	 * Only allow to change name and password.
	 * */
	@RequestMapping(value = "/updateBsUser", method = RequestMethod.POST)
	public BsMessage updateBsUser(@RequestBody BsUser bsUser) {
		logger.info(this.getClass().getSimpleName() + ": updateBsUser");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Update user Unsuccessfully.");

		if (checkUpdateUser(bsUser)) {
			BsUser user = new BsUser();
			user.setUser_name(bsUser.getUser_name());
			user.setUser_email(bsUser.getUser_email());
			user.setUser_pwd(bsUser.getUser_pwd());
			if (bsUserService.updateBsUser(user) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Update user successfully.");
			}
		}
		return bsMsg;
	}

	/**
	 * To check data: email is required.
	 * User name or User password is required.
	 * */
	private boolean checkUpdateUser(BsUser bsUser) {
		boolean result = false;
		if (bsUser != null) {
			if (bsUser.getUser_email() != null && !bsUser.getUser_email().equals("")
					&& ((bsUser.getUser_name() != null && !bsUser.getUser_name().equals(""))
							|| (bsUser.getUser_pwd() != null && !bsUser.getUser_pwd().equals("")))) {
				result = true;
			}
		}
		return result;
	}

	// When you want delete the user, email is required.
	@RequestMapping(value = "/deleteBsUser", method = RequestMethod.POST)
	public BsMessage deleteBsUser(@RequestBody BsUser bsUser) {
		logger.info(this.getClass().getSimpleName() + ": deleteBsUser");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Delete user Unsuccessfully.");
		if (checkDeleteUser(bsUser)) {
			if (bsUserService.deleteBsUser(bsUser) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Delete user successfully.");				
			}
		}		
		return bsMsg;
	}

	// Email is required when delete the user. 
	private boolean checkDeleteUser(BsUser bsUser) {
		boolean result = false;
		if (bsUser != null) {
			if (bsUser.getUser_email() != null && !bsUser.getUser_email().equals("")) {
				result = true;
			}
		}
		return result;
	}

}