package com.troila.lw;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.troila.lw.contract.MyUrl;

//這裡寫的是服務提供者的服務實例名
@FeignClient("spring-feign-provider")
public interface HelloClient {

	/**
	 * 由於spring有註解翻譯器的存在，因此spring會將@RequestMapping註解翻譯為feign可以識別和使用的註解
	 * 類似可以被spring註解翻譯器翻譯後，令Feign識別的註解還有@RequestParam @PathVariable @RequestHeader
	 * 
	 * 當Feign集成到spring中之後，Feign的註解就不能使用了，必須使用spring的註解。
	 * 可以視作這是spring做的標準化。
	 */
	@RequestMapping(value="/hello/{name}", method = RequestMethod.GET)
	//通過測試，發現這個@PathVariable後面必須要寫上參數名稱，否則會報錯。天知道為什麼
	String hello(@PathVariable("name") String name);
	
	@RequestMapping(value = "/call/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	Police getPolice(@PathVariable("id") Integer id);
	
	//這裡的GET就是RequestMethod.GET
	@MyUrl(method = "GET", url = "/helloWithOutArg")
	String myHello() ;
}
