package com.poc.agents.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/service/", headers = Agent1.acceptHeader)
public class Agent1 {
	protected static final String acceptHeader = "Accept=application/json, application/xml, text/xml";
	
	@RequestMapping(value = "/callJob/", method = RequestMethod.GET)
	public @ResponseBody
	String callJob() {
		System.out.println("I am calling job1.......11111.");
		
		DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
				"http://localhost:8088/job1/service/add");
			//HttpPost postRequest = new HttpPost(
			//			"http://localhost:8080/dynamic-data-collection/concept/ebli/add");
			postRequest.setHeader("Accept", "application/xml");

			//StringEntity input = new StringEntity(addRevenueXML);
			StringEntity input = new StringEntity("Hello Job1");
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
	

}
