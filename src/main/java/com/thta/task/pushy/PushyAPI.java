package com.thta.task.pushy;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings({"deprecation", "resource", "unchecked"})
public class PushyAPI {
	public static ObjectMapper mapper = new ObjectMapper();
	
	// Insert your Secret API Key here
	public static final String SECRET_API_KEY = "40167d0aa6be33212d5d60270a10c724a6d0b47a7988bb50d0654b19750b3d54";
	
	public static void sendPush(PushyPushRequest req) throws Exception  {
		// Get custom HTTP client
		HttpClient client = new DefaultHttpClient();
		
		// Create POST request
		HttpPost request = new HttpPost("https://api.pushy.me/push?api_key=" + SECRET_API_KEY);
		
		// Set content type to JSON
		request.addHeader("Content-Type", "application/json");
		
		// Convert post data to JSON
		byte[] json = mapper.writeValueAsBytes(req);
		
		// Send post data as byte array
		request.setEntity(new ByteArrayEntity(json));
		
		// Execute the request
		HttpResponse response = client.execute(request, new BasicHttpContext());
		
		// Get response JSON as string
		String responseJSON = EntityUtils.toString(response.getEntity());
		
		// Convert JSON response into HashMap
		Map<String, Object> map = mapper.readValue(responseJSON, Map.class);
		
		// Got an error?
		if (map.containsKey("error")) {
			// Throw it
			throw new Exception(map.get("error").toString());
		}		
	}
	
	public static class PushyPushRequest {
		public Object to;
		public Object data;
		public Object notification;
		
		public PushyPushRequest(Object data, Object to, Object notification) {
			this.to = to;
			this.data = data;
			this.notification = notification;
		}		
	}

}