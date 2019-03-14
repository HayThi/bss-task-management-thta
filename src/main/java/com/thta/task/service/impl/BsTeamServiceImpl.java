package com.thta.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thta.task.model.BsModal;
import com.thta.task.model.BsTeam;
import com.thta.task.repository.BsTeamRepository;
import com.thta.task.service.BsTeamService;

@Service
public class BsTeamServiceImpl implements BsTeamService {

	@Autowired
	private BsTeamRepository bsTeamRepository;

	@Override
	public List<BsTeam> getAllTeamInfo() {
		return bsTeamRepository.getAllTeamInfo();
	}

	@Override
	public List<BsTeam> getTeamsByUserId(int userId) {
		return bsTeamRepository.getTeamsByUserId(userId);
	}

	@Override
	public int createBsTeam(BsModal modal) {
		return bsTeamRepository.createBsTeam(modal);
	}

	@Override
	public int updateBsTeam(BsTeam team) {
		return bsTeamRepository.updateBsTeam(team);
	}

	@Override
	public int deleteBsTeam(int teamId) {
		return bsTeamRepository.deleteBsTeam(teamId);
	}

	@Override
	public int deleteBsTeamById(int teamId) {
		return bsTeamRepository.deleteBsTeamById(teamId);
	}

	@Override
	public boolean checkTeamByTeamId(int teamId) {
		return bsTeamRepository.checkTeamByTeamId(teamId);
	}

	// To check team title that is required when create team.
	@Override
	public boolean checkCreateTeam(BsModal modal) {
		boolean result = false;
		if (modal != null) {
			if (modal.getUser_id() != 0 && modal.getTeam_name() != null && !modal.getTeam_name().equals("")) {
				result = true;
			}
		}
		return result;
	}

	// To check team title that is required when create team.
	@Override
	public boolean checkUpdateTeam(BsTeam team) {
		boolean result = false;
		if (team != null) {
			if (team.getTeam_id() != 0 && ((team.getTeam_name() != null && !team.getTeam_name().equals(""))
					|| (team.getTeam_desc() != null && !team.getTeam_desc().equals("")))) {
				result = true;
			}
		}
		return result;
	}

}