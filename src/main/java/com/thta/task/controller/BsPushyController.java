package com.thta.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thta.task.pushy.PushyAPI;

@RestController
public class BsPushyController {	

	@GetMapping("/sendPushNoti")
	public static void sendSamplePush() {
		System.out.println("sendSamplePush");
		// Prepare list of target device tokens
		List<String> deviceTokens = new ArrayList<>();
		// Add your device tokens here
		deviceTokens.add("3696d82baacfea6a496792");
		// Convert to String[] array
		String[] to = deviceTokens.toArray(new String[deviceTokens.size()]);
		
		// Optionally, send to a public/subscribe topic instead
		//String to = "/topics/news";
		
		// Set payload (any object, it will be serialized to JSON)
		Map<String, String> payload = new HashMap<>();
		
		// Add "message" parametr to payload
		payload.put("message", "Hello World!");
		
		// iOS notification fileds
		Map<String, Object> notification = new HashMap<>();
		notification.put("badge", 1);
		notification.put("sound", "ping.aiff");
		notification.put("body", "Hello World \u270c");
		
		// Prepare the push request
		PushyAPI.PushyPushRequest push = new PushyAPI.PushyPushRequest(payload, to, notification);
		try {
			// Try sending the push notification
			PushyAPI.sendPush(push);
		} catch (Exception e) {
			// Error, print to console
			System.out.println(e.toString());
		}
		
	}

}