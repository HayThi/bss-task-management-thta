package com.thta.task.service;

import java.util.List;

public interface UserTeamBoardService {

	List<Integer> getAllTeamIdByUserId(int userId);

	List<Integer> getAllBoardIdByUserId(int userId);

	List<Integer> getAllBoardIdByTeamId(int teamId);

	int addUserIdAndTeamId(int userId, int teamId);

	int addUserIdAndBoardId(int userId, int boardId);

	int addTeamIdAndBoardId(int teamId, int boardId);

	int removeByUserId(int userId);

	int removeByTeamId(int teamId);

	int removeByBoardId(int boardId);

}