package com.thta.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thta.task.model.BsModal;
import com.thta.task.model.BsTeam;

/**
 * Integration testing for Team Service.
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BsTeamServiceTest {

	@Autowired
	private BsTeamService bsTeamService;

	// To test when retrieve all teams.
	@Test
	public void getAllTeamInfo() {
		List<BsTeam> teams = bsTeamService.getAllTeamInfo();
		assertThat(teams).isNotNull().isNotEmpty();
	}

	// To test when retrieve get teams by user_id.
	@Test
	public void getTeamsByUserId() {
		List<BsTeam> teams = bsTeamService.getTeamsByUserId(4);
		assertThat(teams).isNotNull().isNotEmpty();
	}

	// To test when create team.
	@Test
	public void createBsTeam() {
		BsModal modal = new BsModal();
		modal.setUser_id(4);
		modal.setTeam_name("create team user 4");
//		modal.setTeam_desc("create team test1");
		int createResult = bsTeamService.createBsTeam(modal);
		assertThat(createResult).isNotEqualTo(0);
	}

	// To test when update team.
	@Test
	public void updateBsTeam() {
		BsTeam team = new BsTeam();
		team.setTeam_id(9);
		team.setTeam_name("modified team name");
//		team.setTeam_desc("Add team description");
		int updateResult = bsTeamService.updateBsTeam(team);
		assertThat(updateResult).isNotEqualTo(0);
	}

	// To test when delete team. This will also delete team_id in the dump.
	@Test
	public void deleteBsTeam() {		
		int deleteResult = bsTeamService.deleteBsTeam(8);
		assertThat(deleteResult).isNotEqualTo(0);
	}

	// To test when delete team by team_id.
	@Test
	public void deleteBsTeamById() {
		int deleteByIdResult = bsTeamService.deleteBsTeamById(9);
		assertThat(deleteByIdResult).isNotEqualTo(0);
	}

	// To test when check team by team_id.
	@Test
	public void checkTeamByTeamId() {
		boolean checkTeamResult = bsTeamService.checkTeamByTeamId(9);
		assertThat(checkTeamResult).isNotEqualTo(false);
	}

}