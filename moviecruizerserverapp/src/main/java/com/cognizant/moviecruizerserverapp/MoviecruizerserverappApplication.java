package com.cognizant.moviecruizerserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cognizant.moviecruizerserverapp.filter.JwtFilter;

@SpringBootApplication
public class MoviecruizerserverappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviecruizerserverappApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		final FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new JwtFilter());
		bean.addUrlPatterns("/api/*");
		return bean;
	}
}
