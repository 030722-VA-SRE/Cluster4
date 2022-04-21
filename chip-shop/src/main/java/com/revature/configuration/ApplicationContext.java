package com.revature.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration 
public class ApplicationContext {
	
	@Bean
	public TimedAspect timeAspect(MeterRegistry registry){
		return new TimedAspect(registry);
	}
	
}
