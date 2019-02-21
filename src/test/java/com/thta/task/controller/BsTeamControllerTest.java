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
import com.thta.task.model.BsModal;
import com.thta.task.model.BsTeam;

/**
 * This is the Team Controller Unit Test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BsTeamController.class)
public class BsTeamControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BsTeamController bsTeamController;

	private Gson gson = new Gson();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(this.bsTeamController).build();
	}

	// To test when getAllTeamInfo URL is called.
	@Test
	public void getAllTeamInfo() throws Exception {
		List<BsTeam> teams = new ArrayList<BsTeam>();
		BsTeam team1 = new BsTeam();
		team1.setTeam_id(1);
		team1.setTeam_name("Team 1");
		team1.setTeam_desc("Team1 Desc");
		BsTeam team2 = new BsTeam();
		team2.setTeam_id(2);
		team2.setTeam_name("Team 2");
		teams.add(team1);
		teams.add(team2);

		BDDMockito.given(bsTeamController.getAllTeamInfo()).willReturn(teams);

		mockMvc.perform(MockMvcRequestBuilders.get("/getAllTeamInfo").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$[0].team_id", is(1)));
	}

	// To test when getTeamsByUserId URL is called.
	@Test
	public void getTeamsByUserId() throws Exception {
		List<BsTeam> teams = new ArrayList<BsTeam>();
		BsTeam team1 = new BsTeam();
		team1.setTeam_id(1);
		team1.setTeam_name("Team 1");
		team1.setTeam_desc("Team1 Desc");
		BsTeam team2 = new BsTeam();
		team2.setTeam_id(2);
		team2.setTeam_name("Team 2");
		teams.add(team1);
		teams.add(team2);

		BsModal modal = new BsModal();
		modal.setUser_id(1);

		BDDMockito.given(bsTeamController.getTeamsByUserId(Matchers.any(BsModal.class))).willReturn(teams);

		mockMvc.perform(MockMvcRequestBuilders.post("/getTeamsByUserId").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(modal)).characterEncoding("utf-8")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].team_id", is(1)));
	}

	// To test when createBsTeam URL is called.
	@Test
	public void createBsTeam() throws Exception {
		BsModal modal = new BsModal();
		modal.setUser_id(4);
		modal.setTeam_name("create team user 4");
		modal.setTeam_desc("create team test1");

		BDDMockito.given(bsTeamController.createBsTeam(Matchers.any(BsModal.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		MvcResult result = mockMvc
				.perform(post("/createBsTeam").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(modal))
						.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(gson.toJson(CommonUtil.getSuccessMsg()))).andDo(print())
				.andReturn();

		String res = result.getResponse().getContentAsString();
		System.err.println("createBsTeam is " + res);
	}

	// To test when updateBsTeam URL is called.
	@Test
	public void updateBsTeam() throws Exception {
		BsTeam team = new BsTeam();
		team.setTeam_id(9);
		team.setTeam_name("modified team name");
		team.setTeam_desc("Add team description");

		BDDMockito.given(bsTeamController.updateBsTeam(Matchers.any(BsTeam.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		mockMvc.perform(post("/updateBsTeam").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(team))
				.characterEncoding("utf-8")).andExpect(status().isOk()).andExpect(jsonPath("$.msg_code", is("200")))
				.andDo(print());
	}

	// To test when deleteBsTeam URL is called.
	@Test
	public void deleteBsTeam() throws Exception {
		BsTeam team = new BsTeam();
		team.setTeam_id(4);

		BDDMockito.given(bsTeamController.deleteBsTeam(Matchers.any(BsTeam.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		mockMvc.perform(post("/deleteBsTeam").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(team))
				.characterEncoding("utf-8")).andExpect(status().isOk()).andExpect(jsonPath("$.msg_code", is("200")))
				.andDo(print());
	}

}