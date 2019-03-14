package com.thta.task.commom_utility;

import java.text.SimpleDateFormat;

public class UserConstant {
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static final String USER_NOT_EXIST = "This user does not exist.";
	public static final String USER_CREATE_FAIL = "User cannot be created.";
	public static final String USER_CREATE_DATA_REQUIRED = "Please fill all data: username, email and password.";
	
	public static final String USER_UPDATE_DATA_REQUIRED = "Please fill username or password.";
	public static final String USER_UPDATE_SUCCESS = "User is successfully updated.";
	public static final String USER_UPDATE_FAIL = "User cannot be updated.";
	
	public static final String USER_DELETE_SUCCESS = "User is successfully deleted.";
	public static final String USER_DELETE_FAIL = "User cannot be deleted.";
	
	
	public static final String TEAM_NOT_EXIST = "This team does not exist.";
	public static final String TEAM_CREATE_REQUIRED = "User id and team name are required.";
	public static final String TEAM_CREATE_FAIL = "Team cannot be created.";
	
	public static final String TEAM_UPDATE_REQUIRED = "Team name or description is required.";
	public static final String TEAM_UPDATE_SUCCESS = "Team is successfully updated.";
	public static final String TEAM_UPDATE_FAIL = "Team cannot be updated.";
	
	public static final String TEAM_DELETE_SUCCESS = "Team is successfully deleted.";
	public static final String TEAM_DELETE_FAIL = "Team cannot be deleted.";
	
	public static final String BOARD_CREATE_REQUIRED = "Please fill user id or team id and board title.";
	public static final String BOARD_CREATE_SUCCESS = "Board is successfully created.";
	public static final String BOARD_CREATE_FAIL = "Board cannot be created.";
	
	public static final String BOARD_NOT_EXIST = "This board does not exist.";
	public static final String BOARD_UPDATE_REQUIRED = "Please fill board title.";
	public static final String BOARD_UPDATE_SUCCESS = "Board is successfully updated.";
	public static final String BOARD_UPDATE_FAIL = "Board cannot be updated.";
	
	public static final String BOARD_DELETE_SUCCESS = "Board is successfully deleted.";
	public static final String BOARD_DELETE_FAIL = "Board cannot be deleted.";
	
}