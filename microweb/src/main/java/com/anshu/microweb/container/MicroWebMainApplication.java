package com.anshu.microweb.container;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//Main Application class is to boostartp the IOC container 
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages={"com.anshu.microweb.authentication","com.anshu.microweb.container"})
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages={"com.anshu.microweb.authentication.repository"})
@EntityScan(basePackages={"com.anshu.microweb.authentication.domain"})
@PropertySource({"file:${APP_HOME}/config/microweb-default.properties","file:${APP_HOME}/config/microweb-${ENV_TYP}.properties"})
public class MicroWebMainApplication {

	protected final static Log logger = LogFactory.getLog(MicroWebMainApplication.class);
	
	public static void main(String[] args) {
		logger.info("run.....");
		SpringApplication.run(MicroWebMainApplication.class, args);
		
	}
	
	
 }
