package com.thta.task.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.thta.task.CommonUtil;
import com.thta.task.model.BsUser;

/**
 * This is the User Controller Unit Test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BsUserController.class)
public class BsUserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BsUserController bsUserController;

	private Gson gson = new Gson();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.bsUserController).build();
	}

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

		BDDMockito.given(bsUserController.getAllBsUser()).willReturn(userList);

		mockMvc.perform(MockMvcRequestBuilders.get("/getAllBsUser").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	// To test when createBsUser URL is called.
	@Test
	public void createBsUser() throws Exception {
		BsUser user = new BsUser();
		user.setUser_name("test");
		user.setUser_email("test@gmail.com");
		user.setUser_pwd("test");

		BDDMockito.given(bsUserController.createBsUser(Matchers.any(BsUser.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		MvcResult result = mockMvc
				.perform(post("/createBsUser").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user))
						.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(gson.toJson(CommonUtil.getSuccessMsg()))).andDo(print())
				.andReturn();

		String res = result.getResponse().getContentAsString();
		System.err.println("createBsUser is " + res);
	}

	// To test when updateBsUser URL is called.
	@Test
	public void updateBsUser() throws Exception {
		BsUser user = new BsUser();
		user.setUser_email("test@gmail.com");
		user.setUser_name("Test");
		user.setUser_pwd("123");

		BDDMockito.given(bsUserController.updateBsUser(Matchers.any(BsUser.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		mockMvc.perform(post("/updateBsUser").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user))
				.characterEncoding("utf-8")).andExpect(status().isOk()).andExpect(jsonPath("$.msg_code", is("2001")))
				.andDo(print());
	}

	// To test when deleteBsUser URL is called.
	@Test
	public void deleteBsUser() throws Exception {
		BsUser user = new BsUser();
		user.setUser_email("test@gmail.com");

		BDDMockito.given(bsUserController.deleteBsUser(Matchers.any(BsUser.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		mockMvc.perform(post("/deleteBsUser").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user))
				.characterEncoding("utf-8")).andExpect(status().isOk()).andExpect(jsonPath("$.msg_code", is("200")))
				.andDo(print());
	}

}