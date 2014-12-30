package com.camunda.bpm.cob.userManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CreateUserGroupMembership {

	public static void main(String[] args) {
		CreateUserGroupMembership createUserGroupMembership = new CreateUserGroupMembership();
		try {
			createUserGroupMembership.run();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void run() throws IOException, URISyntaxException {
		List<String> lines = Files.readAllLines(
			    Paths.get(this.getClass().getResource("/user-group-membership.txt").toURI()), Charset.defaultCharset());
		for (String line : lines) {
			if (line.startsWith("#") || line.trim().length() == 0) {
				// leave it
			} else {
				int separatorPos = line.indexOf(" ");
				if (separatorPos == -1) {
					separatorPos = line.length();
				}
				String method = line.substring(0, separatorPos);
				int separatorPos2 = line.indexOf("{");
				if (separatorPos2 == -1) {
					separatorPos2 = line.length();
				}
				String resource = line.substring(separatorPos, separatorPos2);
				System.out.println(method + resource);
				String payload = line.substring(separatorPos2);
				System.out.println("payload: " + payload);
				
				sendtoHttpServer(method, resource, payload);				
			}
		}
		
	}

	public void sendtoHttpServer(String method, String resource, String payload)
			throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL("http://localhost:8080/engine-rest" + resource.trim());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method.trim());
		conn.setRequestProperty("Content-Type", "application/json");
 
		OutputStream os = conn.getOutputStream();
		os.write(payload.getBytes());
		os.flush();
 
		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED 
				& conn.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT
				& conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
 
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
 
		conn.disconnect();
	}

}
