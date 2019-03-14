package com.thta.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thta.task.model.BsBoard;
import com.thta.task.model.BsModal;
import com.thta.task.repository.BsBoardRepository;
import com.thta.task.service.BsBoardService;
import com.thta.task.service.BsTeamService;
import com.thta.task.service.BsUserService;

@Service
public class BsBoardServiceImpl implements BsBoardService {

	@Autowired
	private BsBoardRepository bsBoardRepository;

	@Autowired
	private BsUserService bsUserService;

	@Autowired
	private BsTeamService bsTeamService;

	@Override
	public List<BsBoard> getAllBsBoards() {
		return bsBoardRepository.getAllBsBoards();
	}

	@Override
	public List<BsBoard> getBoardsByUserId(int userId) {
		return bsBoardRepository.getBoardsByUserId(userId);
	}

	@Override
	public List<BsBoard> getBoardsByTeamId(int teamId) {
		return bsBoardRepository.getBoardsByTeamId(teamId);
	}

	@Override
	public int createBsBoard(BsModal modal) {
		if ((modal.getTeam_id() != 0 && bsTeamService.checkTeamByTeamId(modal.getTeam_id()))
				|| (modal.getUser_id() != 0 && bsUserService.checkUserByUserId(modal.getUser_id()))) {
			return bsBoardRepository.createBsBoard(modal);
		}

		return 0;
	}

	@Override
	public int updateBsBoard(BsBoard board) {

		return bsBoardRepository.updateBsBoard(board);
	}

	@Override
	public int deleteBsBoard(int boardId) {
		return bsBoardRepository.deleteBsBoard(boardId);
	}

	@Override
	public int deleteBsBoardById(int id) {
		return bsBoardRepository.deleteBsBoardById(id);
	}

	@Override
	public boolean checkBoardByBoardId(int boardId) {
		boolean result = false;
		if (boardId != 0) {
			result = bsBoardRepository.checkBoardByBoardId(boardId);			
		}
		return result;
	}

	/**
	 * To check data when create board: user_id or team_id and board_title are
	 * required.
	 */
	@Override
	public boolean checkCreateBoard(BsModal modal) {
		boolean result = false;
		if (modal != null) {
			if ((modal.getUser_id() != 0 || modal.getTeam_id() != 0) && modal.getBoard_title() != null
					&& !modal.getBoard_title().equals("")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * To check board_title is required.
	 * */
	@Override
	public boolean checkUpdateBoard(BsBoard board) {
		boolean result = false;
		if (board != null && board.getBoard_title() != null && !board.getBoard_title().equals("")) {
			result = true;
		}

		return result;
	}

}