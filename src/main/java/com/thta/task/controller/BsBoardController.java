package com.thta.task.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thta.task.commom_utility.UserConstant;
import com.thta.task.model.BsBoard;
import com.thta.task.model.BsModal;
import com.thta.task.service.BsBoardService;
import com.thta.task.service.BsTeamService;
import com.thta.task.service.BsUserService;

/**
 * This is the board controller.
 */
@RestController
@RequestMapping("boards")
public class BsBoardController {

	private Logger logger = LogManager.getLogger(BsBoardController.class);

	@Autowired
	private BsBoardService bsBoardService;

	@Autowired
	private BsUserService bsUserService;

	@Autowired
	private BsTeamService bsTeamService;

	// To get all boards
	@GetMapping
	public ResponseEntity<?> getAllBsBoards() {
		logger.debug(this.getClass().getSimpleName() + ": getAllBsBoards");
		return new ResponseEntity<>(bsBoardService.getAllBsBoards(), HttpStatus.OK);
	}

	// To retrieve all boards according to the user id.
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getBoardsByUserId(@PathVariable("userId") int userId) {
		logger.debug(this.getClass().getSimpleName() + ": getBoardsByUserId");

		if (bsUserService.checkUserByUserId(userId)) {
			return new ResponseEntity<>(bsBoardService.getBoardsByUserId(userId), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(UserConstant.USER_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

	// To retrieve board by team id.
	@GetMapping("/team/{teamId}")
	public ResponseEntity<?> getBoardsByTeamId(@PathVariable("teamId") int teamId) {
		logger.debug(this.getClass().getSimpleName() + ": getBoardsByTeamId");

		if (bsTeamService.checkTeamByTeamId(teamId)) {
			return new ResponseEntity<>(bsBoardService.getBoardsByTeamId(teamId), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(UserConstant.TEAM_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

	// To create board.
	@PostMapping
	public ResponseEntity<?> createBsBoard(@RequestBody BsModal modal) {
		logger.debug(this.getClass().getSimpleName() + ": createBsBoard");

		if (bsBoardService.checkCreateBoard(modal)) {

			if (bsBoardService.createBsBoard(modal) > 0) {
				return new ResponseEntity<>(UserConstant.BOARD_CREATE_SUCCESS, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(UserConstant.BOARD_CREATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>(UserConstant.BOARD_CREATE_REQUIRED, HttpStatus.PRECONDITION_REQUIRED);
		}
	}

	// To update board according to the board id.
	@PutMapping("/{boardId}")
	public ResponseEntity<?> updateBsBoard(@PathVariable("boardId") int boardId, @RequestBody BsBoard board) {
		logger.debug(this.getClass().getSimpleName() + ": updateBsBoard");

		if (bsBoardService.checkBoardByBoardId(boardId)) {
			board.setBoard_id(boardId);
			if (bsBoardService.checkUpdateBoard(board)) {
				if (bsBoardService.updateBsBoard(board) > 0) {
					return new ResponseEntity<>(UserConstant.BOARD_UPDATE_SUCCESS, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(UserConstant.BOARD_UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} else {
				return new ResponseEntity<>(UserConstant.BOARD_UPDATE_REQUIRED, HttpStatus.PRECONDITION_REQUIRED);
			}

		} else {
			return new ResponseEntity<>(UserConstant.BOARD_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

	// To delete the board according to the board id
	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> deleteBsBoard(@PathVariable("boardId") int boardId) {
		logger.debug(this.getClass().getSimpleName() + ": deleteBsBoard");

		if (bsBoardService.checkBoardByBoardId(boardId)) {
			if (bsBoardService.deleteBsBoard(boardId) > 0) {
				return new ResponseEntity<>(UserConstant.BOARD_DELETE_SUCCESS, HttpStatus.CONFLICT);
				
			} else {
				return new ResponseEntity<>(UserConstant.BOARD_DELETE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>(UserConstant.BOARD_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

}