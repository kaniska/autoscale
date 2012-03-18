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

import com.poc.agents.LoadInfo;
import com.poc.common.IJob;

@Controller
@RequestMapping(value = "/service/", headers = Agent2.acceptHeader)
public class Agent2 {
	protected static final String acceptHeader = "Accept=application/json, application/xml, text/xml";
	
	
		@RequestMapping(value = "/callJob/", method = RequestMethod.GET)
		public @ResponseBody
		String callJob() {
			System.out.println("I am calling job1.......11111.");
			
			return "<test>done job1........</test>";
		}
		
		
	/**
	 * 
	 * @param job
	 * @param loadInfo
	 * @return
	 */
	public int callExecution(IJob job, LoadInfo loadInfo) {
		// ExecutionEngine create ExecutionContext
		// return ExecutionResult
		return 0;
	}
	
	// Aggregate Loads
			public int aggregateLoad() {
				LoadAggregator loadAggregator = new LoadAggregator();
				return 0;
			}
	
}
