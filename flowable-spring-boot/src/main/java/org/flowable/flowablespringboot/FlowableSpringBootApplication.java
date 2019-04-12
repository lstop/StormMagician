package org.flowable.flowablespringboot;

import org.flowable.rocketmq.MQProducter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlowableSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowableSpringBootApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(final MyService myService) {

	    return new CommandLineRunner() {
	    	public void run(String... strings) throws Exception {
	        	myService.createDemoUsers();
	        	
	        	MQProducter producter = new MQProducter();
	        	producter.init();
	        }
	    };
	}

}
