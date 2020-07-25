package com.boot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	@Autowired
	private TaskProcessor taskProcessor;
	
	
	@RequestMapping(path = "/demoservice" ,method =  RequestMethod.POST)
	public @ResponseBody String handleRequest(@RequestBody String request)
	{
		System.out.println("pushing msg >> "+request);
		
		taskProcessor.publishTaskRequest(request);
		
		return "message pushed in queue";
	}

}
