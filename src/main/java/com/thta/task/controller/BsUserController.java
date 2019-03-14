package com.thta.task.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thta.task.commom_utility.UserConstant;
import com.thta.task.model.BsUser;
import com.thta.task.service.BsUserService;

/**
 * This is the user controller.
 */
@RestController
@RequestMapping("users")
public class BsUserController {

	private Logger logger = LogManager.getLogger(BsUserController.class);

	@Autowired
	private BsUserService bsUserService;

	// To show all user.
	@GetMapping
	public ResponseEntity<?> getAllBsUser() {
		logger.debug(this.getClass().getSimpleName() + ": getAllBsUser");
		return new ResponseEntity<>(bsUserService.getAllBsUser(), HttpStatus.OK);
	}

	// To create user.
	@PostMapping
	public ResponseEntity<?> createBsUser(@RequestBody BsUser bsUser) {
		logger.debug(this.getClass().getSimpleName() + ": createBsUser");

		if (bsUserService.checkCreateUser(bsUser)) {
			int returnResult = bsUserService.createBsUser(bsUser);
			if (returnResult > 0) {
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(returnResult).toUri();
				return new ResponseEntity<>(location, HttpStatus.CREATED);

			} else {
				return new ResponseEntity<>(UserConstant.USER_CREATE_FAIL, HttpStatus.CONFLICT);
			}

		} else {
			return new ResponseEntity<>(UserConstant.USER_CREATE_DATA_REQUIRED, HttpStatus.PRECONDITION_REQUIRED);
		}
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateBsUser(@PathVariable("userId") int userId, @RequestBody BsUser bsUser) {
		logger.debug(this.getClass().getSimpleName() + ": updateBsUser");

		if (bsUserService.checkUserByUserId(userId)) {
			bsUser.setUser_id(userId);

			if (bsUserService.checkUpdateUser(bsUser)) {

				if (bsUserService.updateBsUser(bsUser) > 0) {
					return new ResponseEntity<>(UserConstant.USER_UPDATE_SUCCESS, HttpStatus.OK);

				} else {
					return new ResponseEntity<>(UserConstant.USER_UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} else {
				return new ResponseEntity<>(UserConstant.USER_UPDATE_DATA_REQUIRED, HttpStatus.PRECONDITION_REQUIRED);
			}

		} else {
			return new ResponseEntity<>(UserConstant.USER_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

	// When you want delete the user, email is required.
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteBsUser(@PathVariable("userId") int userId) {
		logger.debug(this.getClass().getSimpleName() + ": deleteBsUser");

		if (bsUserService.checkUserByUserId(userId)) {
			if (bsUserService.deleteBsUser(userId) > 0) {
				return new ResponseEntity<>(UserConstant.USER_DELETE_SUCCESS, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(UserConstant.USER_DELETE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>(UserConstant.USER_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

}