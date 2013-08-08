package com.exoplatform.cloudworkspaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Small MongoDB application that uses spring data to interact with MongoDB.
 */
public class MongoDBApp {

    static final Logger logger = LoggerFactory.getLogger(MongoDBApp.class);

    public static void main( String[] args ) {
		logger.info("Bootstrapping MongoDemo application");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");

        Tenants tenantRepository = context.getBean(Tenants.class);

        // cleanup tenant collection before insertion
        tenantRepository.dropTenantCollection();

        //create tenant collection
        tenantRepository.createTenantCollection();

//        for(int i=0; i<5; i++) {
//            tenantRepository.insertNewRandomTenant();
//        }

        logger.info("Search one tenant");

        if (tenantRepository.findTenantByName("Mock-tenant") == null) {
            logger.info("Create Mock");
            tenantRepository.insertMockTenant();
        } else {
            logger.info("Delete Mock");
            tenantRepository.deleteTenant("Mock-tenant");
            logger.info("Create Mock");
            tenantRepository.insertMockTenant();
        }

        logger.info(tenantRepository.findTenantByName("Mock-tenant").toString());

        tenantRepository.logAllTenants();

        logger.info("Finished MongoDemo application");
	}
}
