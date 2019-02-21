package com.thta.task.model;

// Team model.
public class BsTeam {

	private int team_id;
	private String team_name;
	private String team_desc;
	private String reg_date;

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_desc() {
		return team_desc;
	}

	public void setTeam_desc(String team_desc) {
		this.team_desc = team_desc;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

}