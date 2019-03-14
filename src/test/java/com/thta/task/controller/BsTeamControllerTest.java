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
import com.thta.task.model.BsModal;
import com.thta.task.model.BsTeam;

/**
 * This is the Team Controller Unit Test.
 */
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BsTeamControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BsTeamController bsTeamController;

	private Gson gson = new Gson();

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

		ResponseEntity entity = new ResponseEntity<List<BsTeam>>(teams, HttpStatus.OK);

		when(bsTeamController.getAllTeamInfo()).thenReturn(entity);

		mockMvc.perform(get("/teams").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
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

		ResponseEntity entity = new ResponseEntity<List<BsTeam>>(teams, HttpStatus.OK);

		when(bsTeamController.getTeamsByUserId(Matchers.anyInt())).thenReturn(entity);

		mockMvc.perform(get("/teams/{userId}", 1).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	// To test when createBsTeam URL is called.
	@Test
	public void createBsTeam() throws Exception {
		BsModal modal = new BsModal();
		modal.setUser_id(4);
		modal.setTeam_name("create team user 4");
		modal.setTeam_desc("create team test1");

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(4).toUri();

		ResponseEntity entity = new ResponseEntity<>(location, HttpStatus.CREATED);

		when(bsTeamController.createBsTeam(Matchers.any(BsModal.class))).thenReturn(entity);

		mockMvc.perform(post("/teams").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(modal))).andDo(print())
				.andExpect(status().isCreated());
	}

	// To test when updateBsTeam URL is called.
	@Test
	public void updateBsTeam() throws Exception {
		BsTeam team = new BsTeam();
		team.setTeam_id(9);
		team.setTeam_name("modified team name");
		team.setTeam_desc("Add team description");
		
		ResponseEntity entity = new ResponseEntity<>(UserConstant.TEAM_UPDATE_SUCCESS, HttpStatus.OK);

		when(bsTeamController.updateBsTeam(Matchers.anyInt(), Matchers.any(BsTeam.class))).thenReturn(entity);

		mockMvc.perform(put("/teams/9")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(team)))
				.andDo(print())
				.andExpect(status().isOk());
	}

	// To test when deleteBsTeam URL is called.
	@Test
	public void deleteBsTeam() throws Exception {
				
		ResponseEntity entity = new ResponseEntity<>(UserConstant.TEAM_DELETE_SUCCESS, HttpStatus.OK);

		when(bsTeamController.deleteBsTeam(Matchers.anyInt())).thenReturn(entity);

		mockMvc.perform(delete("/teams/9")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

}