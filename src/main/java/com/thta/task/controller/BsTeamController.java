package com.thta.task.controller;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thta.task.commom_utility.UserConstant;
import com.thta.task.model.BsModal;
import com.thta.task.model.BsTeam;
import com.thta.task.service.BsTeamService;
import com.thta.task.service.BsUserService;

/**
 * This is the team controller.
 */
@RestController
@RequestMapping("teams")
public class BsTeamController {

	private Logger logger = LogManager.getLogger(BsTeamController.class);

	@Autowired
	private BsTeamService bsTeamService;

	@Autowired
	private BsUserService bsUserService;

	// To retrieve all team data
	@GetMapping
	public ResponseEntity<?> getAllTeamInfo() {
		logger.debug(this.getClass().getSimpleName() + ": getAllTeamInfo");
		return new ResponseEntity<>(bsTeamService.getAllTeamInfo(), HttpStatus.OK);
	}

	// To retrieve all teams data according to the user_id
	@GetMapping(value = "/{userId}")
	public ResponseEntity<?> getTeamsByUserId(@PathVariable("userId") int userId) {
		logger.debug(this.getClass().getSimpleName() + ": getTeamsByUserId");

		if (bsUserService.checkUserByUserId(userId)) {
			return new ResponseEntity<>(bsTeamService.getTeamsByUserId(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(UserConstant.USER_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * To create team. user_id and team_name are required.
	 */
	@PostMapping
	public ResponseEntity<?> createBsTeam(@RequestBody BsModal modal) {
		logger.debug(this.getClass().getSimpleName() + ": createBsTeam");

		if (bsTeamService.checkCreateTeam(modal)) {

			if (bsUserService.checkUserByUserId(modal.getUser_id())) {
				int returnResult = bsTeamService.createBsTeam(modal);
				if (returnResult > 0) {
					URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
							.buildAndExpand(returnResult).toUri();
					return new ResponseEntity<>(location, HttpStatus.CREATED);

				} else {
					return new ResponseEntity<>(UserConstant.TEAM_CREATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} else {
				return new ResponseEntity<>(UserConstant.USER_NOT_EXIST, HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<>(UserConstant.TEAM_CREATE_REQUIRED, HttpStatus.PRECONDITION_REQUIRED);
		}
	}

	// Update team data.
	@PutMapping("/{teamId}")
	public ResponseEntity<?> updateBsTeam(@PathVariable("teamId") int teamId, @RequestBody BsTeam team) {
		logger.debug(this.getClass().getSimpleName() + ": updateBsTeam");

		if (bsTeamService.checkTeamByTeamId(teamId)) {
			team.setTeam_id(teamId);

			if (bsTeamService.checkUpdateTeam(team)) {

				if (bsTeamService.updateBsTeam(team) > 0) {
					return new ResponseEntity<>(UserConstant.TEAM_UPDATE_SUCCESS, HttpStatus.OK);

				} else {
					return new ResponseEntity<>(UserConstant.TEAM_UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} else {
				return new ResponseEntity<>(UserConstant.TEAM_UPDATE_REQUIRED, HttpStatus.PRECONDITION_REQUIRED);
			}

		} else {
			return new ResponseEntity<>(UserConstant.TEAM_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * To delete the team. When delete the team, all boards will also delete the
	 * according to the team_id.
	 */
	@DeleteMapping("/{teamId}")
	public ResponseEntity<?> deleteBsTeam(@PathVariable("teamId") int teamId) {
		logger.debug(this.getClass().getSimpleName() + ": deleteBsTeam");

		if (bsTeamService.checkTeamByTeamId(teamId)) {
			if (bsTeamService.deleteBsTeam(teamId) > 0) {
				return new ResponseEntity<>(UserConstant.TEAM_DELETE_SUCCESS, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(UserConstant.TEAM_DELETE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);				
			}

		} else {
			return new ResponseEntity<>(UserConstant.TEAM_NOT_EXIST, HttpStatus.NOT_FOUND);
		}
	}

}