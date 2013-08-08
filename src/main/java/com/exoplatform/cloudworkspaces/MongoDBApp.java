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

        Tenants tenantRepository = context.getBean(Tenants.class);

        // cleanup tenant collection before insertion
        //tenantRepository.dropTenantCollection();

        //create tenant collection
        tenantRepository.createTenantCollection();

        for(int i=0; i<5; i++) {
            tenantRepository.insertNewRandomTenant();
        }

        tenantRepository.logAllTenants();
       // logger.info("Avarage age of a tenant is: {}", tenantRepository.());

        logger.info("Search one tenant");

        if (tenantRepository.findTenantByName("Mock-tenant") == null) {
            logger.info("Create Mock");
            tenantRepository.insertMockTenant();
        }

        logger.info(tenantRepository.findTenantByName("Mock-tenant").toString());

        logger.info("Finished MongoDemo application");
	}
}
