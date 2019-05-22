package com.troila.lw.contract;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

//當前類的作用，就是告訴spring容器，有一個我們自定義的翻譯器的存在 
@Configuration
public class MyConfig {

	@Bean
	public Contract feignContract() {
		return new MyContract();
	}
}
