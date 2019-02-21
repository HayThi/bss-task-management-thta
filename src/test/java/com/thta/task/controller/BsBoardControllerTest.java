package com.thta.task.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.thta.task.CommonUtil;
import com.thta.task.model.BsBoard;
import com.thta.task.model.BsModal;

/**
 * This is the Board Controller Unit Test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BsBoardController.class)
public class BsBoardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BsBoardController bsBoardController;

	private Gson gson = new Gson();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bsBoardController).build();
	}

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

		BDDMockito.given(bsBoardController.getAllBsBoards()).willReturn(boards);

		mockMvc.perform(MockMvcRequestBuilders.get("/getAllBsBoards").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$[0].board_id", is(1)));
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

		BsModal modal = new BsModal();
		modal.setUser_id(1);

		BDDMockito.given(bsBoardController.getBoardsByUserId(Matchers.any(BsModal.class))).willReturn(boards);

		mockMvc.perform(MockMvcRequestBuilders.post("/getBoardsByUserId").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(modal)).characterEncoding("utf-8")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].board_title", is("Board 1")));
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

		BsModal modal = new BsModal();
		modal.setTeam_id(1);

		BDDMockito.given(bsBoardController.getBoardsByTeamId(Matchers.any(BsModal.class))).willReturn(boards);

		mockMvc.perform(MockMvcRequestBuilders.post("/getBoardsByTeamId").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(modal)).characterEncoding("utf-8")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[1].board_id", is(2)));
	}

	// To test when createBsBoard URL is called.
	@Test
	public void createBsBoard() throws Exception {
		BsModal modal = new BsModal();
		modal.setUser_id(1);
		modal.setBoard_title("Create board by UserId 1");
		// modal.setTeam_id(1);
		// modal.setBoard_title("Create board by TeamId 1");

		BDDMockito.given(bsBoardController.createBsBoard(Matchers.any(BsModal.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		MvcResult result = mockMvc
				.perform(post("/createBsBoard").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(modal))
						.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(gson.toJson(CommonUtil.getSuccessMsg()))).andDo(print())
				.andReturn();

		String res = result.getResponse().getContentAsString();
		System.err.println("createBsBoard is " + res);
	}

	// To test when updateBsBoard URL is called.
	@Test
	public void updateBsBoard() throws Exception {
		BsBoard board = new BsBoard();
		board.setBoard_id(12);
		board.setBoard_title("Update Board title");

		BDDMockito.given(bsBoardController.updateBsBoard(Matchers.any(BsBoard.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		mockMvc.perform(post("/updateBsBoard").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(board))
				.characterEncoding("utf-8")).andExpect(status().isOk()).andExpect(jsonPath("$.msg_code", is("200")))
				.andDo(print());
	}

	// To test when deleteBsBoard URL is called.
	@Test
	public void deleteBsBoard() throws Exception {
		BsBoard board = new BsBoard();
		board.setBoard_id(12);

		BDDMockito.given(bsBoardController.deleteBsBoard(Matchers.any(BsBoard.class)))
				.willReturn(CommonUtil.getSuccessMsg());

		mockMvc.perform(post("/deleteBsBoard").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(board))
				.characterEncoding("utf-8")).andExpect(status().isOk()).andExpect(jsonPath("$.msg_code", is("200")))
				.andDo(print());
	}

}