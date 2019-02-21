package com.thta.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thta.task.model.BsBoard;
import com.thta.task.model.BsModal;
import com.thta.task.repository.BsBoardRepository;
import com.thta.task.service.BsBoardService;

@Service
public class BsBoardServiceImpl implements BsBoardService{
	
	@Autowired
	BsBoardRepository bsBoardRepository;
	
	@Override
	public List<BsBoard> getAllBsBoards(){
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
		return bsBoardRepository.createBsBoard(modal);		
	}

	@Override
	public int updateBsBoard(BsBoard board) {
		return bsBoardRepository.updateBsBoard(board);		
	}

	@Override
	public int deleteBsBoard(BsBoard board) {
		return bsBoardRepository.deleteBsBoard(board);		
	}
	
	@Override
	public int deleteBsBoardById(int id) {
		return bsBoardRepository.deleteBsBoardById(id);
	}
	
	@Override
	public boolean checkBoardByBoardId(int boardId) {
		return bsBoardRepository.checkBoardByBoardId(boardId);	
	}

}