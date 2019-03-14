package com.thta.task.repository;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.thta.task.model.BsBoard;
import com.thta.task.model.BsModal;
import com.thta.task.service.BsTeamService;
import com.thta.task.service.BsUserService;
import com.thta.task.service.UserTeamBoardService;

@Repository
public class BsBoardRepository {

	@Autowired
	private JdbcTemplate jdbcTemplateOne;

	@Autowired
	private BsUserService bsUserService;

	@Autowired
	private BsTeamService bsTeamService;

	@Autowired
	private UserTeamBoardService userTeamBoardService;

	// Check board by board id.
	public boolean checkBoardByBoardId(int boardId) {
		boolean result = false;

		String sql = "SELECT * FROM bs_board WHERE board_id = ?";
		RowMapper<BsBoard> rowMapper = new BeanPropertyRowMapper<BsBoard>(BsBoard.class);
		try {
			jdbcTemplateOne.queryForObject(sql, rowMapper, boardId);
			result = true;
		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			result = false;
		}

		return result;
	}

	// Get all boards.
	public List<BsBoard> getAllBsBoards() {
		List<BsBoard> boards = new ArrayList<BsBoard>();
		List<Map<String, Object>> rows = jdbcTemplateOne.queryForList("SELECT * FROM bs_board");
		for (Map<String, Object> row : rows) {
			BsBoard board = new BsBoard();
			board.setBoard_id((int) row.get("board_id"));
			board.setBoard_title((String) row.get("board_title"));
			board.setReg_date((String) row.get("reg_date"));
			boards.add(board);
		}
		return boards;
	}

	// Get all boards by user id.
	public List<BsBoard> getBoardsByUserId(int userId) {
		List<BsBoard> boards = new ArrayList<BsBoard>();
		// To check user id
		if (bsUserService.checkUserByUserId(userId)) {
			String sql = "SELECT DISTINCT bs_board.* FROM bs_board INNER JOIN user_team_board ON user_team_board.board_id = bs_board.board_id WHERE user_team_board.user_id ="
					+ userId;

			List<Map<String, Object>> rows = jdbcTemplateOne.queryForList(sql);
			for (Map<String, Object> row : rows) {
				BsBoard board = new BsBoard();
				board.setBoard_id((int) row.get("board_id"));
				board.setBoard_title((String) row.get("board_title"));
				board.setReg_date((String) row.get("reg_date"));
				boards.add(board);
			}
		}

		return boards;
	}

	// Get all boards by team id.
	public List<BsBoard> getBoardsByTeamId(int teamId) {
		List<BsBoard> boards = new ArrayList<BsBoard>();
		if (bsTeamService.checkTeamByTeamId(teamId)) {
			String sql = "SELECT DISTINCT bs_board.* FROM bs_board INNER JOIN user_team_board ON user_team_board.board_id = bs_board.board_id WHERE user_team_board.team_id ="
					+ teamId;

			List<Map<String, Object>> rows = jdbcTemplateOne.queryForList(sql);
			for (Map<String, Object> row : rows) {
				BsBoard board = new BsBoard();
				board.setBoard_id((int) row.get("board_id"));
				board.setBoard_title((String) row.get("board_title"));
				board.setReg_date((String) row.get("reg_date"));
				boards.add(board);
			}
		}

		return boards;
	}

	// To create board.
	public int createBsBoard(BsModal modal) {
		int result = 0;
		try {
			if (modal.getTeam_id() != 0) {
				int teamId = modal.getTeam_id();
				int boardId = getBoardIdAfterCreatingBoard(modal.getBoard_title());
				result = userTeamBoardService.addTeamIdAndBoardId(teamId, boardId);

			} else if (modal.getUser_id() != 0) {
				int userId = modal.getUser_id();
				int boardId = getBoardIdAfterCreatingBoard(modal.getBoard_title());
				result = userTeamBoardService.addUserIdAndBoardId(userId, boardId);
			}

		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			result = 0;
		}

		return result;
	}

	// To retrieve board id after creating the board.
	private int getBoardIdAfterCreatingBoard(String boardTitle) {
		SimpleDateFormat dateformater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String todayDateStr = dateformater.format(new Date());
		String createBoard = "INSERT INTO bs_board (board_title, reg_date) VALUES (?, ?)";
		jdbcTemplateOne.update(createBoard, boardTitle, todayDateStr);

		Map<String, Object> row = jdbcTemplateOne.queryForMap("SELECT LAST_INSERT_ID()");
		return ((BigInteger) row.get("LAST_INSERT_ID()")).intValue();
	}

	// To update the board.
	public int updateBsBoard(BsBoard board) {
		int result = 0;
		try {
			String qUpdate = "UPDATE bs_board ";
			String qSet = "SET board_title = '" + board.getBoard_title() + "' ";
			String qWhere = "WHERE board_id = " + board.getBoard_id();
			result = jdbcTemplateOne.update(qUpdate + qSet + qWhere);

		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			result = 0;
		}

		return result;
	}

	// To delete the board and also delete the board id in the user_team_board.
	public int deleteBsBoard(int boardId) {
		int result = 0;
		try {
			if (deleteBsBoardById(boardId) > 0) {
				return userTeamBoardService.removeByBoardId(boardId);
			}

		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			result = 0;

		}

		return result;
	}

	// To delete board by board id.
	public int deleteBsBoardById(int id) {
		int result = 0;
		try {
			String sql = "DELETE FROM bs_board WHERE board_id=?";
			result = jdbcTemplateOne.update(sql, id);

		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			result = 0;
		}

		return result;
	}

}