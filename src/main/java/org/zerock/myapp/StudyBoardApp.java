package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@NoArgsConstructor

@EntityScan
@EnableJpaRepositories

@ServletComponentScan
@SpringBootApplication
public class StudyBoardApp extends ServletInitializer{

	public static void main(String[] args) {
//		SpringApplication.run(StudyBoardApp.class, args);
		
		SpringApplication app = new SpringApplication(StudyBoardApp.class);
		
//		app.setWebApplicationType(WebApplicationType.SERVLET);
		app.setWebApplicationType(WebApplicationType.NONE);
		
//		app.addListeners(new ApplicationListener<ApplicationEvent>() {
//			
//			@Override
//			public void onApplicationEvent(ApplicationEvent event) {
//				log.trace("onApplicationEvent({}) invoked.", event.getClass().getSimpleName());
//				
//			} // onApplicationEvent
//			
//		}); // .addListeners
	
		app.setBannerMode(Mode.CONSOLE);
		app.addListeners(e -> log.trace("oneApplicationEvent({}) invoked.", e.getClass().getSimpleName()));
		app.run(args);
		
		log.trace("main ({}) invoked.", Arrays.toString(args));
	} // main

} // end class
