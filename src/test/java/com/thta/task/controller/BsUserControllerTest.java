package com.thta.task.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.thta.task.commom_utility.UserConstant;
import com.thta.task.model.BsUser;

/**
 * This is the User Controller Unit Test.
 */
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BsUserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BsUserController bsUserController;

	private Gson gson = new Gson();

	// To test when getAllBsUser URL is called.
	@Test
	public void getAllBsUser() throws Exception {
		List<BsUser> userList = new ArrayList<BsUser>();
		BsUser user1 = new BsUser();
		user1.setUser_id(1);
		user1.setUser_name("thu");
		user1.setUser_email("thu@gmail.com");
		user1.setUser_pwd("thu");
		BsUser user2 = new BsUser();
		user2.setUser_id(2);
		user2.setUser_name("hay");
		user2.setUser_email("hay@gmail.com");
		user2.setUser_pwd("hay");
		userList.add(user1);
		userList.add(user2);

		ResponseEntity entity = new ResponseEntity<List<BsUser>>(userList, HttpStatus.OK);

		when(bsUserController.getAllBsUser()).thenReturn(entity);

		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	// To test when createBsUser URL is called.
	@Test
	public void createBsUser() throws Exception {
		BsUser user = new BsUser();
		user.setUser_name("test");
		user.setUser_email("test@gmail.com");
		user.setUser_pwd("test");

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(11).toUri();

		ResponseEntity entity = new ResponseEntity<>(location, HttpStatus.CREATED);

		when(bsUserController.createBsUser(Matchers.any(BsUser.class))).thenReturn(entity);

		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)))
				.andDo(print()).andExpect(status().isCreated());
	}

	// To test when updateBsUser URL is called.
	@Test
	public void updateBsUser() throws Exception {
		BsUser user = new BsUser();
		user.setUser_id(1);
		user.setUser_name("Test");
		user.setUser_pwd("123");

		ResponseEntity entity = new ResponseEntity<>(UserConstant.USER_UPDATE_SUCCESS, HttpStatus.OK);

		when(bsUserController.updateBsUser(Matchers.anyInt(), Matchers.any(BsUser.class))).thenReturn(entity);

		mockMvc.perform(put("/users/{userId}", 1)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8").content(gson.toJson(user)))
		.andDo(print())
		.andExpect(status().isOk());
	}

	// To test when deleteBsUser URL is called.
	@Test
	public void deleteBsUser() throws Exception {

		 ResponseEntity entity = new
		 ResponseEntity<>(UserConstant.USER_DELETE_SUCCESS, HttpStatus.OK);
		
		 when(bsUserController.deleteBsUser(Matchers.anyInt())).thenReturn(entity);
		
		 mockMvc.perform(delete("/users/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
		 .andExpect(status().isOk());
	}

}