package com.thta.task;

import com.thta.task.model.BsMessage;

public class CommonUtil {

	public static BsMessage getSuccessMsg() {
		BsMessage bsSuccessMsg = new BsMessage();
		bsSuccessMsg.setMsg_code("200");
		bsSuccessMsg.setMsg_title("Success");
		bsSuccessMsg.setMsg_desc("Create user successfully.");
		return bsSuccessMsg;
	}
	
	public static BsMessage getErrorMsg() {
		BsMessage bsErrorMsg = new BsMessage();
		bsErrorMsg.setMsg_code("404");
		bsErrorMsg.setMsg_title("Warning");
		bsErrorMsg.setMsg_desc("Create user Unsuccessfully.");
		return bsErrorMsg;
	}

}