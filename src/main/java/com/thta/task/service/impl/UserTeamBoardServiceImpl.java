package com.thta.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thta.task.repository.UserTeamBoardRepository;
import com.thta.task.service.UserTeamBoardService;

@Service
public class UserTeamBoardServiceImpl implements UserTeamBoardService {

	@Autowired
	private UserTeamBoardRepository userTeamBoardRepository;

	@Override
	public List<Integer> getAllTeamIdByUserId(int userId) {
		return userTeamBoardRepository.getAllTeamIdByUserId(userId);
	}
	
	@Override
	public List<Integer> getAllBoardIdByUserId(int userId) {
		return userTeamBoardRepository.getAllBoardIdByUserId(userId);
	}

	@Override
	public List<Integer> getAllBoardIdByTeamId(int teamId) {
		return userTeamBoardRepository.getAllBoardIdByTeamId(teamId);
	}
	
	@Override
	public int addUserIdAndTeamId(int userId,int teamId) {
		return userTeamBoardRepository.addUserIdAndTeamId(userId, teamId);		
	}
	
	@Override
	public int addUserIdAndBoardId(int userId,int boardId) {
		return userTeamBoardRepository.addUserIdAndBoardId(userId, boardId);
	}
	
	@Override
	public int addTeamIdAndBoardId(int teamId,int boardId) {
		return userTeamBoardRepository.addTeamIdAndBoardId(teamId, boardId);
	}
	
	@Override
	public int removeByUserId(int userId) {
		return userTeamBoardRepository.removeByUserId(userId);
	}
	
	@Override
	public int removeByTeamId(int teamId) {
		return userTeamBoardRepository.removeByTeamId(teamId);
	}
	
	@Override
	public int removeByBoardId(int boardId) {
		return userTeamBoardRepository.removeByBoardId(boardId);
	}

}
