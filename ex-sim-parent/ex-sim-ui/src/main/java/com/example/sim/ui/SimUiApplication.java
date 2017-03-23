/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package com.example.sim.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import feign.Feign;
import feign.Logger;
import feign.Request;
/**
 *  注解说明：
 *	@SpringBootApplication相当于@Configuration、@EnableAutoConfiguration、@ComponentScan三个注解合用。
 *	@EnableEurekaClient配置本应用将使用服务注册和服务发现，注意：注册和发现用这一个注解。
 *	@EnableHystrix表示启用断路器，断路器依赖于服务注册和发现。
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients
public class SimUiApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SimUiApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}

	@Bean
	public Logger.Level feignLogger() {
		return Logger.Level.FULL;
	}

	private static final int FIVE_SECONDS = 5000;

	@Bean
	public Request.Options options() {
		return new Request.Options(FIVE_SECONDS, FIVE_SECONDS);
	}

}
