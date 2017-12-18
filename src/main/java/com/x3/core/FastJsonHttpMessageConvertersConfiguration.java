package com.x3.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class FastJsonHttpMessageConvertersConfiguration {

	@Bean	
	public FastJsonHttpMessageConverter JsonHttpMessageConverter() {
		 return new FastJsonHttpMessageConverter();
		}
	
}
