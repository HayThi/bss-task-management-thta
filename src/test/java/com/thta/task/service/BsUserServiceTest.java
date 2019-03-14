package com.thta.task.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thta.task.model.BsUser;

/**
 * Integration testing for User Service.
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BsUserServiceTest {
	
	@Autowired
	private BsUserService bsUserService;
	
	// To test when retrieve all users.
	@Test
	public void getAllBsUser() {
		List<BsUser> userList = bsUserService.getAllBsUser();
		assertThat(userList).isNotNull().isNotEmpty();		
	}
	
	// To test when create the user.
	@Test
	public void createBsUser() {
		BsUser user = new BsUser();
		user.setUser_name("test");
		user.setUser_email("test@gmail.com");
		user.setUser_pwd("test");
		int createResult = bsUserService.createBsUser(user);
		assertThat(createResult).isNotEqualTo(0);
	}
	
	// To test when update the user.
	@Test
	public void updateBsUser() {
		BsUser user = new BsUser();
		user.setUser_email("test@gmail.com");
//		user.setUser_name("Test");
		user.setUser_pwd("123");
		int updateResult = bsUserService.updateBsUser(user);
		assertThat(updateResult).isNotEqualTo(0);		
	}
	
	// To test when delete the user. 
	@Test
	public void deleteBsUser() {
		BsUser user = new BsUser();		
		user.setUser_email("test@gmail.com");
		int createResult = bsUserService.deleteBsUser(1);
		assertThat(createResult).isNotEqualTo(0);
	}
	
	// To test when check user email.
	@Test
	public void checkUserEmail () {		
		boolean checkUserEmailResult = bsUserService.checkUserEmail("thi@gmail.com");
		assertThat(checkUserEmailResult).isNotEqualTo(false);
	}
	
	// To test when retrieve user_id by user email.
	@Test
	public void getUserIdByUserEmail() {
		int getUserIdResult = bsUserService.getUserIdByUserEmail("thi@gmail.com");
		assertThat(getUserIdResult).isNotEqualTo(0);
	}
	
	// To test when check user by user id.
	@Test
	public void checkUserByUserId() {
		boolean checkUserResult = bsUserService.checkUserByUserId(5);
		assertThat(checkUserResult).isNotEqualTo(false);
	}

}