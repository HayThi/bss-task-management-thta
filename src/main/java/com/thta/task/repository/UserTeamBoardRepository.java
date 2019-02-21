package com.thta.task.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserTeamBoardRepository {

	@Autowired
	private JdbcTemplate jdbcTemplateOne;

	public List<Integer> getAllTeamIdByUserId(int userId) {
		List<Integer> teamIdList = new ArrayList<Integer>();
		List<Map<String, Object>> rows = jdbcTemplateOne
				.queryForList("SELECT DISTINCT team_id FROM user_team_board WHERE user_id = " + userId);
		for (Map<String, Object> row : rows) {
			if (row.get("team_id") != null) {
				teamIdList.add((int) row.get("team_id"));				
			}			 
		}
		return teamIdList;
	}
	
	public List<Integer> getAllBoardIdByUserId(int userId) {
		List<Integer> boardIdList = new ArrayList<Integer>();
		List<Map<String, Object>> rows = jdbcTemplateOne
				.queryForList("SELECT DISTINCT board_id FROM user_team_board WHERE user_id = " + userId);
		for (Map<String, Object> row : rows) {
			if ( row.get("board_id") != null) {
				boardIdList.add((int) row.get("board_id"));				
			}			 
		}
		return boardIdList;
		
	}

	public List<Integer> getAllBoardIdByTeamId(int teamId) {
		List<Integer> boardIdList = new ArrayList<Integer>();
		List<Map<String, Object>> rows = jdbcTemplateOne
				.queryForList("SELECT DISTINCT board_id FROM user_team_board WHERE team_id = " + teamId);
		for (Map<String, Object> row : rows) {
			if ( row.get("board_id") != null) {
				boardIdList.add((int) row.get("board_id"));				
			}			 
		}
		return boardIdList;
	}
	
	public int addUserIdAndTeamId(int userId,int teamId) {
		String sql = "INSERT INTO user_team_board (user_id, team_id) VALUES (?, ?)";
		return jdbcTemplateOne.update(sql, userId, teamId);		
	}
	
	public int addUserIdAndBoardId(int userId,int boardId) {
		String sql = "INSERT INTO user_team_board (user_id, board_id) VALUES (?, ?)";
		return jdbcTemplateOne.update(sql, userId, boardId);		
	}
	
	public int addTeamIdAndBoardId(int teamId,int boardId) {
		String sql = "INSERT INTO user_team_board (team_id, board_id) VALUES (?, ?)";
		return jdbcTemplateOne.update(sql, teamId, boardId);		
	}
	
	public int removeByUserId(int userId) {
		return jdbcTemplateOne.update("DELETE FROM user_team_board WHERE user_id=?", userId);		
	}
	
	public int removeByTeamId(int teamId) {
		return jdbcTemplateOne.update("DELETE FROM user_team_board WHERE team_id=?", teamId);		
	}
	
	public int removeByBoardId(int boardId) {
		return jdbcTemplateOne.update("DELETE FROM user_team_board WHERE board_id=?", boardId);		
	}

}