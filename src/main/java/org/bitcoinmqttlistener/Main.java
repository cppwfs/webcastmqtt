package org.bitcoinmqttlistener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@EnableAutoConfiguration
@ComponentScan
@ImportResource("integration.xml")
public class Main {

	@Value("${displayMessage}")
	String displayMessage ;
	int counter = 0;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class,args);
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return displayMessage + counter;
	}

	@ServiceActivator(inputChannel = "output")
	public void counter(String value) {
		System.out.println(value);
		counter++;
	}

}
