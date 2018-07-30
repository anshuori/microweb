package com.anshu.microweb.container.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SunriseCommandLineRunner implements CommandLineRunner {


	@Value("${my.abc1}")
	private String name;
	
	@Autowired
	private Environment env;
	
	@Override
	public void run(String... args) throws Exception {	
		System.out.println("SunriseCommandLineRunner>XXXXXXXXXX>"+name);
		System.out.println("SunriseCommandLineRunner>XXXXXXXXXX>"+env.getProperty("my.name1"));
		System.out.println("SunriseCommandLineRunner>XXXXXXXXXX>"+env.getProperty("my.name2"));
		System.out.println("SunriseCommandLineRunner>XXXXXXXXXX>"+env.getProperty("my.xyz"));
		
	}


	@Scheduled(initialDelay = 1000, fixedRate = 40000)
	public void run() throws Exception {	
		System.out.println("SunriseCommandLineRunner>SHHEDULING>>>XXXXXXXXXX>");	

	}

}
