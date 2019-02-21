package com.thta.task.service;

import java.util.List;

import com.thta.task.model.BsModal;
import com.thta.task.model.BsTeam;

public interface BsTeamService {

	List<BsTeam> getAllTeamInfo();

	List<BsTeam> getTeamsByUserId(int userId);

	int createBsTeam(BsModal modal);

	int updateBsTeam(BsTeam team);

	int deleteBsTeam(BsTeam team);

	int deleteBsTeamById(int teamId);

	boolean checkTeamByTeamId(int teamId);

}