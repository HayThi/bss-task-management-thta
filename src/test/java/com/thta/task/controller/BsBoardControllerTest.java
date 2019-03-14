package com.thta.task.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.google.gson.Gson;
import com.thta.task.commom_utility.UserConstant;
import com.thta.task.model.BsBoard;
import com.thta.task.model.BsModal;

/**
 * This is the Board Controller Unit Test.
 */
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BsBoardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BsBoardController bsBoardController;

	private Gson gson = new Gson();

	// To test when getAllBsBoards URL is called.
	@Test
	public void getAllBoards() throws Exception {
		List<BsBoard> boards = new ArrayList<BsBoard>();
		BsBoard board1 = new BsBoard();
		board1.setBoard_id(1);
		board1.setBoard_title("Board 1");
		BsBoard board2 = new BsBoard();
		board2.setBoard_id(2);
		board2.setBoard_title("Board 2");
		boards.add(board1);
		boards.add(board2);

		ResponseEntity entity = new ResponseEntity<List<BsBoard>>(boards, HttpStatus.OK);

		when(bsBoardController.getAllBsBoards()).thenReturn(entity);

		mockMvc.perform(get("/boards").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	// To test when getBoardsByUserId URL is called.
	@Test
	public void getBoardsByUserId() throws Exception {
		List<BsBoard> boards = new ArrayList<BsBoard>();
		BsBoard board1 = new BsBoard();
		board1.setBoard_id(1);
		board1.setBoard_title("Board 1");
		BsBoard board2 = new BsBoard();
		board2.setBoard_id(2);
		board2.setBoard_title("Board 2");
		boards.add(board1);
		boards.add(board2);

		ResponseEntity entity = new ResponseEntity<List<BsBoard>>(boards, HttpStatus.OK);

		when(bsBoardController.getBoardsByUserId(Matchers.anyInt())).thenReturn(entity);

		mockMvc.perform(get("/boards/user/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	// To test when getBoardsByTeamId URL is called.
	@Test
	public void getBoardsByTeamId() throws Exception {
		List<BsBoard> boards = new ArrayList<BsBoard>();
		BsBoard board1 = new BsBoard();
		board1.setBoard_id(1);
		board1.setBoard_title("Board 1");
		BsBoard board2 = new BsBoard();
		board2.setBoard_id(2);
		board2.setBoard_title("Board 2");
		boards.add(board1);
		boards.add(board2);

		ResponseEntity entity = new ResponseEntity<List<BsBoard>>(boards, HttpStatus.OK);

		when(bsBoardController.getBoardsByUserId(Matchers.anyInt())).thenReturn(entity);

		mockMvc.perform(get("/boards/team/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	// To test when createBsBoard URL is called.
	@Test
	public void createBsBoard() throws Exception {
		BsModal modal = new BsModal();
		modal.setUser_id(1);
		modal.setBoard_title("Create board by UserId 1");
		// modal.setTeam_id(1);
		// modal.setBoard_title("Create board by TeamId 1");

		ResponseEntity entity = new ResponseEntity<>(UserConstant.BOARD_CREATE_SUCCESS, HttpStatus.OK);

		when(bsBoardController.createBsBoard(Matchers.any(BsModal.class))).thenReturn(entity);

		mockMvc.perform(post("/boards").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(modal)))
				.andDo(print()).andExpect(status().isOk());
	}

	// To test when updateBsBoard URL is called.
	@Test
	public void updateBsBoard() throws Exception {
		BsBoard board = new BsBoard();
		board.setBoard_id(12);
		board.setBoard_title("Update Board title");

		ResponseEntity entity = new ResponseEntity<>(UserConstant.BOARD_UPDATE_SUCCESS, HttpStatus.OK);

		when(bsBoardController.updateBsBoard(Matchers.anyInt(), Matchers.any(BsBoard.class))).thenReturn(entity);

		mockMvc.perform(put("/boards/12").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(board)))
				.andDo(print()).andExpect(status().isOk());
	}

	// To test when deleteBsBoard URL is called.
	@Test
	public void deleteBsBoard() throws Exception {
		BsBoard board = new BsBoard();
		board.setBoard_id(12);
		board.setBoard_title("Board");

		ResponseEntity entity = new ResponseEntity<>(UserConstant.BOARD_DELETE_SUCCESS, HttpStatus.OK);

		when(bsBoardController.deleteBsBoard(Matchers.anyInt())).thenReturn(entity);

		mockMvc.perform(delete("/boards/12").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

}