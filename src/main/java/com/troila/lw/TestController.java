package com.troila.lw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.troila.lw.contract.MyUrl;

@RestController
public class TestController {
	
	@Autowired
	private HelloClient helloClient;
	
	@RequestMapping(value="/router", method = RequestMethod.GET)
	public String router() {
		String result = helloClient.hello("dark");
		return result;
	}
	
	@RequestMapping(value = "/police", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Police getPolice() {
		Police police = helloClient.getPolice(2);
		return police;
	}
	
	//注意，雖然HelloClient類中myHello方法使用的是註解時MyUrl。但是，我們在controller中不要使用MyUrl註解
	//因為那個註解僅僅能出現在@FeignClient中。而不能出現在@Controller中，切切！
//	@MyUrl(method = "GET", url = "/myHello")
	@RequestMapping(value="/myHello", method = RequestMethod.GET)
	public String myHello() {
		return helloClient.myHello();
	}
}
