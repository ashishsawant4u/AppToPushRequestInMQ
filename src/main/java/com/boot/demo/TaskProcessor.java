package com.boot.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component	
@EnableBinding(Source.class)
public class TaskProcessor {

	@Autowired
	Source source;
	
	
	public void publishTaskRequest(String payload)
	{
		String url = "maven://com.boot:CloudTaskDemo:jar:0.0.1-SNAPSHOT";
		
		TaskLaunchRequest launchRequest = new TaskLaunchRequest(url,Arrays.asList(payload), null, null, null);
		
		GenericMessage<TaskLaunchRequest> message = new GenericMessage<TaskLaunchRequest>(launchRequest);
		
		System.out.println("publishTaskRequest  >> "+message);
		
		boolean flag = this.source.output().send(message);
		
		System.out.println("CHANNEL FLAG >> "+flag);
	}
	
}
