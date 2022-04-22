package com.revature.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration 
public class ApplicationContext {
	
	@Bean
	public TimedAspect timeAspect(MeterRegistry registry){
		return new TimedAspect(registry);
	}
	
	@Bean
	public CountedAspect countedAspect(MeterRegistry registry) {
		return new CountedAspect(registry);
	}
}
