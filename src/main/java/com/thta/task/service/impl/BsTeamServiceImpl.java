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
	BsTeamRepository bsTeamRepository;

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
	public int deleteBsTeam(BsTeam team) {
		return bsTeamRepository.deleteBsTeam(team);
	}
	
	@Override
	public int deleteBsTeamById(int teamId) {
		return bsTeamRepository.deleteBsTeamById(teamId);
	}

	@Override
	public boolean checkTeamByTeamId(int teamId) {
		return bsTeamRepository.checkTeamByTeamId(teamId);
	}

}