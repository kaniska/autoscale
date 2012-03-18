package com.poc.agents.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/service/", headers = Agent1.acceptHeader)
public class Agent1 {
	protected static final String acceptHeader = "Accept=application/json, application/xml, text/xml";
	
	@RequestMapping(value = "/distributeLoad/", method = RequestMethod.POST)
	public @ResponseBody
	String callJob(@RequestBody String payloadData) {
		System.out.println("I am calling job1.......11111.");
		
		DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
				"http://localhost:8088/job1/operation/add");
			//HttpPost postRequest = new HttpPost(
			//			"http://localhost:8080/dynamic-data-collection/concept/ebli/add");
			postRequest.setHeader("Accept", "application/xml");

			StringEntity input = new StringEntity(payloadData);
			//StringEntity input = new StringEntity("Hello Job1");
			input.setContentType("application/xml");
			input.setContentEncoding("UTF-8");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			System.out.println("New Response headers ---");
			System.out.println("Response ---" + response.getEntity());
			
			org.apache.http.Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i].toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		
		return "<test>5677</test>";
	}
	
	// Evaluate Load
			public int splitLoad() {
				LoadEvaluator loadEvaluator = new LoadEvaluator();
				return 0;
			}
	
			
			// Distribute Load
			public int distributeLoad() {
				LoadDistributor loadDistributor = new LoadDistributor();
				return 0;
			}
	
			
			private static String readFileAsString(String filePath)
				    throws java.io.IOException{
				        StringBuffer fileData = new StringBuffer(1000);
				        BufferedReader reader = null;
				        try {
				        	 reader = new BufferedReader(
				                     new FileReader(filePath));
				             char[] buf = new char[1024];
				             int numRead=0;
							while((numRead=reader.read(buf)) != -1){
							    String readData = String.valueOf(buf, 0, numRead);
							    fileData.append(readData);
							    buf = new char[1024];
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							reader.close();
						}
				        return fileData.toString();
				    }

}
