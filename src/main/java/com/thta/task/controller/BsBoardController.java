package com.thta.task.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thta.task.model.BsBoard;
import com.thta.task.model.BsMessage;
import com.thta.task.model.BsModal;
import com.thta.task.service.BsBoardService;

/**
 * This is the board controller.
 * */
@RestController
public class BsBoardController {
	
	private Logger logger = LogManager.getLogger(BsBoardController.class);

	@Autowired
	BsBoardService bsBoardService;

	// To get all boards
	@RequestMapping("/getAllBsBoards")
	public List<BsBoard> getAllBsBoards() {
		logger.info(this.getClass().getSimpleName() + ": getAllBsBoards");
		return bsBoardService.getAllBsBoards();
	}
	
	// To retrieve all boards according to the user id.
	@RequestMapping(value="/getBoardsByUserId", method = RequestMethod.POST)
	public List<BsBoard> getBoardsByUserId(@RequestBody BsModal modal) {
		logger.info(this.getClass().getSimpleName() + ": getBoardsByUserId");
		
		if (checkUserId(modal)) {
			return bsBoardService.getBoardsByUserId(modal.getUser_id());
			
		} else {
			return new ArrayList<BsBoard>();		
		}		
	}
	
	// To check user id.
	private boolean checkUserId(BsModal modal) {
		boolean result = false;
		if (modal != null) {
			if (modal.getUser_id() != 0) {
				result = true;
			}		
		}
		return result;
	}
	
	// To retrieve board by team id.
	@RequestMapping(value="/getBoardsByTeamId", method = RequestMethod.POST)
	public List<BsBoard> getBoardsByTeamId(@RequestBody BsModal modal) {
		logger.info(this.getClass().getSimpleName() + ": getBoardsByTeamId");
		
		if (checkTeamId(modal)) {
			return bsBoardService.getBoardsByTeamId(modal.getTeam_id());
			
		} else {
			return new ArrayList<BsBoard>();
		}
	}
	
	// To check team id.
	private boolean checkTeamId(BsModal modal) {
		boolean result = false;
		if (modal != null) {
			if (modal.getTeam_id() != 0) {
				result = true;
			}		
		}
		return result;
	}

	// To create board.
	@RequestMapping(value = "/createBsBoard", method = RequestMethod.POST)
	public BsMessage createBsBoard(@RequestBody BsModal modal) {
		logger.info(this.getClass().getSimpleName() + ": createBsBoard");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Create Board Unsuccessfully.");
		if (checkCreateBoard(modal)) {
			if (bsBoardService.createBsBoard(modal) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Create Board Successfully.");
			}
		}
		return bsMsg;
	}

	/**
	 * To check data when create board: user_id or team_id and board_title are required.
	 * */ 
	private boolean checkCreateBoard(BsModal modal) {
		boolean result = false;
		if (modal != null) {
			if ((modal.getUser_id() != 0 || modal.getTeam_id() != 0) && modal.getBoard_title() != null
					&& !modal.getBoard_title().equals("")) {
				result = true;
			}
		}
		return result;
	}

	// To update board according to the board id.
	@RequestMapping(value = "/updateBsBoard", method = RequestMethod.POST)
	public BsMessage updateBsBoard(@RequestBody BsBoard board) {
		logger.info(this.getClass().getSimpleName() + ": updateBsBoard");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Update Board Unsuccessfully.");
		if (checkUpdateBoard(board)) {
			if (bsBoardService.updateBsBoard(board) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Update Board Successfully.");
			}
		}
		return bsMsg;
	}

	// Check data: board_id and board_title are required.
	private boolean checkUpdateBoard(BsBoard board) {
		boolean result = false;
		if (board != null) {
			if (board.getBoard_id() != 0 && board.getBoard_title() != null && !board.getBoard_title().equals("")) {
				result = true;
			}
		}
		return result;
	}

	//To delete the board according to the board id
	@RequestMapping(value = "/deleteBsBoard", method = RequestMethod.POST)
	public BsMessage deleteBsBoard(@RequestBody BsBoard board) {
		logger.info(this.getClass().getSimpleName() + ": deleteBsBoard");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Delete Board Unsuccessfully.");
		if (board != null && board.getBoard_id() != 0) {
			if (bsBoardService.deleteBsBoard(board) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Delete Board Successfully.");
			}
		}
		return bsMsg;
	}

}