package com.thta.task.service;

import java.util.List;

import com.thta.task.model.BsBoard;
import com.thta.task.model.BsModal;

public interface BsBoardService {

	List<BsBoard> getAllBsBoards();

	List<BsBoard> getBoardsByUserId(int userId);

	List<BsBoard> getBoardsByTeamId(int teamId);

	int createBsBoard(BsModal modal);

	int updateBsBoard(BsBoard board);

	int deleteBsBoard(BsBoard board);

	int deleteBsBoardById(int id);

	boolean checkBoardByBoardId(int boardId);

}