 package com.troila.lw;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class InvokerApp {

	public static void main(String[] args) {
//		new SpringApplicationBuilder(PersonServer.class).web(true).run(args);
		
		Scanner scan = new Scanner(System.in);
		String port = scan.nextLine();
		//下面這裡可以通過自己指定properties，作用相當於在yml配置文件中添加相同的內容
		new SpringApplicationBuilder(InvokerApp.class).properties("server.port=" + port).run(args);
	}

}
