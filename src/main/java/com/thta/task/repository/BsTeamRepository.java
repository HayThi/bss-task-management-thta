package com.thta.task.repository;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.thta.task.model.BsModal;
import com.thta.task.model.BsTeam;
import com.thta.task.service.BsBoardService;
import com.thta.task.service.BsUserService;
import com.thta.task.service.UserTeamBoardService;

@Repository
public class BsTeamRepository {

	@Autowired
	private JdbcTemplate jdbcTemplateOne;

	@Autowired
	BsUserService bsUserService;

	@Autowired
	BsBoardService bsBoardService;

	@Autowired
	UserTeamBoardService userTeamBoardService;

	// To check team is exist or not.
	public boolean checkTeamByTeamId(int teamId) {
		String sql = "SELECT * FROM bs_team WHERE team_id = ?";
		RowMapper<BsTeam> rowMapper = new BeanPropertyRowMapper<BsTeam>(BsTeam.class);
		try {
			jdbcTemplateOne.queryForObject(sql, rowMapper, teamId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// To read all teams
	public List<BsTeam> getAllTeamInfo() {
		List<BsTeam> teams = new ArrayList<BsTeam>();
		List<Map<String, Object>> rows = jdbcTemplateOne.queryForList("SELECT * FROM bs_team");
		for (Map<String, Object> row : rows) {
			BsTeam team = new BsTeam();
			team.setTeam_id((int) row.get("team_id"));
			team.setTeam_name((String) row.get("team_name"));
			team.setTeam_desc((String) row.get("team_desc"));
			team.setReg_date((String) row.get("reg_date"));
			teams.add(team);
		}
		return teams;
	}

	// To get team list by user_id
	public List<BsTeam> getTeamsByUserId(int userId) {
		List<BsTeam> teams = new ArrayList<BsTeam>();
		if (bsUserService.checkUserByUserId(userId)) {
			String sql = "SELECT DISTINCT bs_team.* FROM bs_team INNER JOIN user_team_board ON user_team_board.team_id = bs_team.team_id WHERE user_team_board.user_id="
					+ userId;
			List<Map<String, Object>> rows = jdbcTemplateOne.queryForList(sql);
			for (Map<String, Object> row : rows) {
				BsTeam team = new BsTeam();
				team.setTeam_id((int) row.get("team_id"));
				team.setTeam_name((String) row.get("team_name"));
				team.setTeam_desc((String) row.get("team_desc"));
				team.setReg_date((String) row.get("reg_date"));
				teams.add(team);
			}
		}

		return teams;
	}

	public int createBsTeamByUserEmail(BsModal modal) {
		if (bsUserService.checkUserEmail(modal.getUser_email())) {
			int userId = bsUserService.getUserIdByUserEmail(modal.getUser_email());
			int teamId = getTeamIdAfterCreateTeam(modal);
			return userTeamBoardService.addUserIdAndTeamId(userId, teamId);
		}
		return 0;
	}

	// To create team
	public int createBsTeam(BsModal modal) {
		if (bsUserService.checkUserByUserId(modal.getUser_id())) {
			int userId = modal.getUser_id();
			int teamId = getTeamIdAfterCreateTeam(modal);
			return userTeamBoardService.addUserIdAndTeamId(userId, teamId);
		}
		return 0;
	}

	// To get team id after creating team.
	private int getTeamIdAfterCreateTeam(BsModal modal) {
		SimpleDateFormat dateformater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String todayDateStr = dateformater.format(new Date());
		String createTeam = "";

		if (modal.getTeam_desc() != null && !modal.getTeam_desc().equals("")) {
			createTeam = "INSERT INTO bs_team (team_name, team_desc, reg_date) VALUES (?, ?, ?)";
			jdbcTemplateOne.update(createTeam, modal.getTeam_name(), modal.getTeam_desc(), todayDateStr);
		} else {
			createTeam = "INSERT INTO bs_team (team_name, reg_date) VALUES (?, ?)";
			jdbcTemplateOne.update(createTeam, modal.getTeam_name(), todayDateStr);
		}
		Map<String, Object> row = jdbcTemplateOne.queryForMap("SELECT LAST_INSERT_ID()");
		return ((BigInteger) row.get("LAST_INSERT_ID()")).intValue();
	}

	// To update team
	public int updateBsTeam(BsTeam team) {
		String qUpdate = "UPDATE bs_team ";
		String qSet = "";
		String qWhere = "WHERE team_id = " + team.getTeam_id();
		String updateUser = "";

		if (team.getTeam_name() != null && !team.getTeam_name().equals("") && team.getTeam_desc() != null
				&& !team.getTeam_desc().equals("")) {
			qSet = "SET team_name = '" + team.getTeam_name() + "', team_desc = '" + team.getTeam_desc() + "' ";
		} else if (team.getTeam_name() != null && !team.getTeam_name().equals("")) {
			qSet = "SET team_name = '" + team.getTeam_name() + "' ";
		} else if (team.getTeam_desc() != null && !team.getTeam_desc().equals("")) {
			qSet = "SET team_desc = '" + team.getTeam_desc() + "' ";
		}
		updateUser = qUpdate + qSet + qWhere;
		return jdbcTemplateOne.update(updateUser);
	}

	// To delete team. This will delete all team_id in the user_team_board.
	public int deleteBsTeam(BsTeam team) {
		// Before deleting the team, need to check board that is connected with team.
		List<Integer> boardIdList = userTeamBoardService.getAllBoardIdByTeamId(team.getTeam_id());
		if (boardIdList.size() > 0) {
			// To delete board id connected with deleted team id
			for (int i = 0; i < boardIdList.size(); i++) {
				int deletedResult = bsBoardService.deleteBsBoardById(boardIdList.get(i)); // To delete in the board table.
				System.out.println("THTA: " + deletedResult);
			}
		}

		// After deleting board, remove the team id from the dummy table.
		userTeamBoardService.removeByTeamId(team.getTeam_id());
		return deleteBsTeamById(team.getTeam_id());
	}

	// To delete team by id
	public int deleteBsTeamById(int teamId) {
		String sql = "DELETE FROM bs_team WHERE team_id=?";
		return jdbcTemplateOne.update(sql, teamId);
	}

}