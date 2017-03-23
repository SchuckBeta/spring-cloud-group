/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */
package com.example.sim.ui.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import cloud.simple.service.UserServiceProvider.FeignUserService;

import com.example.sim.ui.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserFeignService {
	 @Autowired
	 RestTemplate restTemplate;

//	 @Autowired
//	 FeignUserService feignUserService;

	 private final String SERVICE_NAME="cloud-simple-service";

	 @HystrixCommand(fallbackMethod = "fallbackSearchAll")
	 public List<User> readUserInfo() {
	        return restTemplate.getForObject("http://"+SERVICE_NAME+"/user", List.class);
		 //return feignUserService.readUserInfo();
	 }

	private List<User> fallbackSearchAll() {
             System.out.println("HystrixCommand fallbackMethod handle!");
             List<User> ls = new ArrayList<User>();
             User user = new User();
             user.setUsername("TestHystrix");
             ls.add(user);
             return ls;

	}
}