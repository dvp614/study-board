package org.zerock.myapp.listener;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j

@NoArgsConstructor

@Component("customApplicationListener")
public class CustomApplicationListener implements ApplicationListener<ApplicationEvent> {

    @PostConstruct
    void postConstruct(){
        log.trace("postConstruct() invoked.");

    } // postConstruct
    @PreDestroy
    void preDestroy(){
        log.trace("preDestroy() invoked.");

    } // preDestroy

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.trace("onApplicationEvent({}) invoked.", event.getClass().getName());
    } // onApplicationEvent
} // end class
