package com.thta.task.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.thta.task.commom_utility.UserConstant;
import com.thta.task.model.BsUser;
import com.thta.task.service.BsBoardService;
import com.thta.task.service.BsTeamService;
import com.thta.task.service.UserTeamBoardService;

@Repository
public class BsUserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplateOne;

	@Autowired
	private BsBoardService bsBoardService;

	@Autowired
	private BsTeamService bsTeamService;

	@Autowired
	private UserTeamBoardService userTeamBoardService;

	/**
	 * Create user. Before creating, check email is already exit or not.
	 */
	public int createBsUser(BsUser user) {
		int result = 0;
		try {

			String createUser = "INSERT INTO bs_user (user_name, user_email, user_pwd, reg_date) VALUES (?, ?, ?, ?)";
			jdbcTemplateOne.update(createUser, user.getUser_name(), user.getUser_email(), user.getUser_pwd(),
					UserConstant.DATE_FORMAT.format(new Date()));
			Map<String, Object> row = jdbcTemplateOne.queryForMap("SELECT LAST_INSERT_ID()");
			result = ((BigInteger) row.get("LAST_INSERT_ID()")).intValue();

		} catch (Exception e) {
			result = 0;
			// print Stack Trace
			e.printStackTrace(System.out);
		}

		return result;
	}

	// To update user info; only name and password.
	public int updateBsUser(BsUser user) {
		int result = 0;

		String qUpdate = "UPDATE bs_user ";
		String qSet = "";
		String qWhere = " WHERE user_id = '" + user.getUser_id() + "'";
		String updateUser = "";
		String name = "";
		String pwd = "";

		try {
			if (user.getUser_name() != null && !user.getUser_name().equals("")) {
				name = user.getUser_name();
			}

			if (user.getUser_pwd() != null && !user.getUser_pwd().equals("")) {
				pwd = user.getUser_pwd();
			}

			if (!name.equals("") && !pwd.equals("")) {
				qSet = "SET user_name = '" + name + "', user_pwd = '" + pwd + "'";
				updateUser = qUpdate + qSet + qWhere;
				result = jdbcTemplateOne.update(updateUser);

			} else if (!name.equals("")) {
				qSet = "SET user_name = '" + name + "'";
				updateUser = qUpdate + qSet + qWhere;
				result = jdbcTemplateOne.update(updateUser);

			} else if (!pwd.equals("")) {
				qSet = "SET user_pwd = '" + pwd + "'";
				updateUser = qUpdate + qSet + qWhere;
				result = jdbcTemplateOne.update(updateUser);
			}

		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			result = 0;
		}

		return result;
	}

	// Get all users.
	public List<BsUser> getAllBsUser() {
		List<BsUser> users = new ArrayList<BsUser>();
		List<Map<String, Object>> rows = jdbcTemplateOne.queryForList("SELECT * FROM bs_user");
		for (Map<String, Object> row : rows) {
			BsUser user = new BsUser();
			user.setUser_id((int) row.get("user_id"));
			user.setUser_name((String) row.get("user_name"));
			user.setUser_email((String) row.get("user_email"));
			user.setUser_pwd((String) row.get("user_pwd"));
			user.setReg_date((String) row.get("reg_date"));
			users.add(user);
		}

		return users;
	}

	// To delete user. When delete user, all team and board will delete.
	public int deleteBsUser(int userId) {
		int result = 0;

		try {
			// Check board connected with deleted user.
			List<Integer> boardIds = userTeamBoardService.getAllBoardIdByUserId(userId);
			if (boardIds.size() > 0) {
				for (int i = 0; i < boardIds.size(); i++) {
					bsBoardService.deleteBsBoardById(boardIds.get(i));
				}
			}

			// Check team connected with deleted user.
			List<Integer> teamIds = userTeamBoardService.getAllTeamIdByUserId(userId);
			if (teamIds.size() > 0) {
				for (int i = 0; i < teamIds.size(); i++) {
					bsTeamService.deleteBsTeam(teamIds.get(i));
				}
			}

			// After deleting board and team, delete user id from the dummy table.
			userTeamBoardService.removeByUserId(userId);

			// After that delete user in the user table.
			result = deleteUserByUserId(userId);

		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			result = 0;
		}

		return result;
	}

	// To check unique email can create new user.
	public boolean checkUserEmail(String email) {
		if (getUserIdByUserEmail(email) > 0) {
			return true;
		} else {
			return false;
		}
	}

	// Check the user by user id.
	public boolean checkUserByUserId(int userId) {
		boolean result = false;
		String checkEmailSql = "SELECT * FROM bs_user WHERE user_id = ?";
		RowMapper<BsUser> rowMapper = new BeanPropertyRowMapper<BsUser>(BsUser.class);
		try {
			jdbcTemplateOne.queryForObject(checkEmailSql, rowMapper, userId);
			result = true;
		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			result = false;
		}

		return result;
	}

	// Get user id by user email.
	public int getUserIdByUserEmail(String email) {
		int result = 0;
		String checkEmailSql = "SELECT user_id FROM bs_user WHERE user_email = ?";
		RowMapper<BsUser> rowMapper = new BeanPropertyRowMapper<BsUser>(BsUser.class);
		try {
			BsUser user = jdbcTemplateOne.queryForObject(checkEmailSql, rowMapper, email);
			result = user.getUser_id();
		} catch (Exception e) {
			// print Stack Trace
			e.printStackTrace(System.out);
			return 0;
		}

		return result;
	}

	/**
	 * Required method.
	 */

	// Delete by user id.
	private int deleteUserByUserId(int userId) {
		String sql = "DELETE FROM bs_user WHERE user_id=?";
		return jdbcTemplateOne.update(sql, userId);
	}

}