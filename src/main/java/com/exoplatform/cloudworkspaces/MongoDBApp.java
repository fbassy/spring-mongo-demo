package com.exoplatform.cloudworkspaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Small MongoDB application that uses spring data to interact with MongoDB.
 * 
 * @author Jeroen Reijn
 */
public class MongoDBApp {

    static final Logger logger = LoggerFactory.getLogger(MongoDBApp.class);

    public static void main( String[] args ) {
		logger.info("Bootstrapping MongoDemo application");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");

        Tenants personRepository = context.getBean(Tenants.class);

        // cleanup person collection before insertion
        personRepository.dropTenantCollection();

        //create person collection
        personRepository.createTenantCollection();

        for(int i=0; i<5; i++) {
            personRepository.insertNewRandomTenant();
        }

        personRepository.logAllTenants();
       // logger.info("Avarage age of a person is: {}", personRepository.());

        logger.info("Finished MongoDemo application");
	}
}
