package org.zerock.myapp.common;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@NoArgsConstructor
public abstract class CommonBeanCallbacks implements InitializingBean, DisposableBean, BeanNameAware{
	protected String beanName;
	
	
	@Override
	public void setBeanName(String name) {
		log.trace("setBeanName({}) invoked.", name);
		
		this.beanName = name;
	} // setBeanName
	
	@PostConstruct
	void postContruct() {
		log.trace("postContstuct() invoked.");
		
	} // postContruct
	
	@PreDestroy
	void preDestroy() {
		log.trace("preDestroy() invoked.");
		
	} // preDestroy
	
	@Override
	public void destroy(){
		log.trace("destroy() invoked.");
		
	} // destroy

	@Override
	public void afterPropertiesSet(){
		log.trace("afterProperitesSet() invoked.");
		
	} // afterPropertieset

	
} // end class
