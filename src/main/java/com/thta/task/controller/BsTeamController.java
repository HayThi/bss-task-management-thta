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

import com.thta.task.model.BsMessage;
import com.thta.task.model.BsModal;
import com.thta.task.model.BsTeam;
import com.thta.task.service.BsTeamService;

/**
 * This is the team controller.
 * */
@RestController
public class BsTeamController {
	
	private Logger logger = LogManager.getLogger(BsTeamController.class);

	@Autowired
	BsTeamService bsTeamService;

	// To retrieve all team data
	@RequestMapping("/getAllTeamInfo")
	public List<BsTeam> getAllTeamInfo() {
		logger.info(this.getClass().getSimpleName() + ": getAllTeamInfo");
		return bsTeamService.getAllTeamInfo();
	}

	// To retrieve all teams data according to the user_id
	@RequestMapping(value = "/getTeamsByUserId", method = RequestMethod.POST)
	public List<BsTeam> getTeamsByUserId(@RequestBody BsModal modal) {
		logger.info(this.getClass().getSimpleName() + ": getTeamsByUserId");
		
		if (checkUserId(modal)) {
			return bsTeamService.getTeamsByUserId(modal.getUser_id());
		} else {
			return new ArrayList<BsTeam>();
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

	/**
	 * To create team.
	 * user_id and team_name are required. 
	 * */
	@RequestMapping(value = "/createBsTeam", method = RequestMethod.POST)
	public BsMessage createBsTeam(@RequestBody BsModal modal) {
		logger.info(this.getClass().getSimpleName() + ": createBsTeam");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Create Team Unsuccessfully.");

		if (checkCreateTeam(modal)) {
			if (bsTeamService.createBsTeam(modal) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Create user successfully.");
			}
		}
		return bsMsg;
	}

	// To check team title that is required when create team.
	private boolean checkCreateTeam(BsModal modal) {
		boolean result = false;
		if (modal != null) {
			if (modal.getUser_id() != 0 && modal.getTeam_name() != null && !modal.getTeam_name().equals("")) {
				result = true;
			}
		}
		return result;
	}

	// Update team data.
	@RequestMapping(value = "/updateBsTeam", method = RequestMethod.POST)
	public BsMessage updateBsTeam(@RequestBody BsTeam team) {
		logger.info(this.getClass().getSimpleName() + ": updateBsTeam");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Create Team Unsuccessfully.");
		System.out.println(team);
		System.out.println(team.getTeam_id());

		if (checkUpdateTeam(team)) {
			if (bsTeamService.updateBsTeam(team) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Create user successfully.");
			}
		}
		return bsMsg;
	}

	// To check team title that is required when create team.
	private boolean checkUpdateTeam(BsTeam team) {
		boolean result = false;
		if (team != null) {
			if (team.getTeam_id() != 0 && ((team.getTeam_name() != null && !team.getTeam_name().equals(""))
					|| (team.getTeam_desc() != null && !team.getTeam_desc().equals("")))) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * To delete the team.
	 * When delete the team, all boards will also delete the according to the team_id.
	 * */ 
	@RequestMapping(value = "/deleteBsTeam", method = RequestMethod.POST)
	public BsMessage deleteBsTeam(@RequestBody BsTeam team) {
		logger.info(this.getClass().getSimpleName() + ": deleteBsTeam");
		
		BsMessage bsMsg = new BsMessage();
		bsMsg.setMsg_code("404");
		bsMsg.setMsg_title("Warning");
		bsMsg.setMsg_desc("Delete Board Unsuccessfully.");
		if (team.getTeam_id() != 0) {
			if (bsTeamService.deleteBsTeam(team) > 0) {
				bsMsg.setMsg_code("200");
				bsMsg.setMsg_title("Success");
				bsMsg.setMsg_desc("Delete Board Successfully.");
			}
		}
		return bsMsg;
	}

}