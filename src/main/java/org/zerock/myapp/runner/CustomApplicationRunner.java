package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j

@NoArgsConstructor

@Component("customApplicationRunner")
public class CustomApplicationRunner implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args){
		log.trace("run({}) invoked.", args);
	} // run

} // end class
