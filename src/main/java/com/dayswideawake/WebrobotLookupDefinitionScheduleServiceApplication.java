package com.dayswideawake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.dayswideawake.frontend.messaging.Channels;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(value=Channels.class)
public class WebrobotLookupDefinitionScheduleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebrobotLookupDefinitionScheduleServiceApplication.class, args);
	}
	
	
	
}
