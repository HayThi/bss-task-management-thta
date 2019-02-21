package com.thta.task.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thta.task.model.BsBoard;
import com.thta.task.model.BsModal;

/**
 * Integration testing for Board Service.
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BsBoardServiceTest {

	@Autowired
	BsBoardService bsBoardService;

	// To test when retrieve all boards.
	@Test
	public void getAllBsBoards() {
		List<BsBoard> boards = bsBoardService.getAllBsBoards();
		assertThat(boards).isNotNull().isNotEmpty();
	}

	// To test when retrieve all boards by user id.
	@Test
	public void getBoardsByUserId() {
		List<BsBoard> boards = bsBoardService.getBoardsByUserId(4);
		assertThat(boards).isNotNull().isNotEmpty();
	}

	// To test when retrieve all boards by team id.
	@Test
	public void getBoardsByTeamId() {
		List<BsBoard> boards = bsBoardService.getBoardsByTeamId(7);
		assertThat(boards).isNotNull().isNotEmpty();
	}

	// To test when create the board.
	@Test
	public void createBsBoard() {
		BsModal modal = new BsModal();
		// modal.setUser_id(3);
		// modal.setBoard_title("Test board create by User id");
		modal.setTeam_id(7);
		modal.setBoard_title("Test board create by team id third time");
		int createResult = bsBoardService.createBsBoard(modal);
		assertThat(createResult).isNotEqualTo(0);
	}

	// To test when update the board.
	@Test
	public void updateBsBoard() {
		BsBoard board = new BsBoard();
		board.setBoard_id(12);
		board.setBoard_title("Update Board title");
		int updateResult = bsBoardService.updateBsBoard(board);
		assertThat(updateResult).isNotEqualTo(0);
	}

	// To test when delete the board. This will also delete board_id in the dump.
	@Test
	public void deleteBsBoard() {
		BsBoard board = new BsBoard();
		board.setBoard_id(12);
		int deleteResult = bsBoardService.deleteBsBoard(board);
		assertThat(deleteResult).isNotEqualTo(0);
	}

	// To test when delete the board by id.
	@Test
	public void deleteBsBoardById() {
		int deleteResult = bsBoardService.deleteBsBoardById(13);
		assertThat(deleteResult).isNotEqualTo(0);
	}

	// To test board by board id.
	@Test
	public void checkBoardByBoardId() {
		boolean checkBoardResult = bsBoardService.checkBoardByBoardId(11);
		assertThat(checkBoardResult).isNotEqualTo(false);
	}

}