package com.exoplatform.cloudworkspaces;

import org.slf4j.Logger;
import com.exoplatform.cloudworkspaces.domain.Tenant;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Repository for {@link Tenants}
 *
 */
@Repository
public class Tenants {

    static Logger logger = LoggerFactory.getLogger(Tenants.class);

    @Autowired
    MongoTemplate mongoTemplate;

    public void logAllTenants() {
        List<Tenant> results = mongoTemplate.findAll(Tenant.class);
        logger.info("Total amount of Tenant: {}", results.size());

     /*   Iterator<Tenant> TenantIterator = results.iterator();
        while (TenantIterator.hasNext()) {
            Tenant nextTenant = TenantIterator.next();
            logger.info(nextTenant.toString());
        }     */

       // logger.info("Results: {}", results.toString());
    }

    public void insertNewRandomTenant() {
        //get random age between 1 and 100
        int number = (int)(Math.random() * 100);

        Tenant p = new Tenant("tenant-"+number, number,number);

        mongoTemplate.insert(p);
    }

    /**
     * Create a {@link Tenant} collection if the collection does not already exists
     */
    public void createTenantCollection() {
        if (!mongoTemplate.collectionExists(Tenant.class)) {
            mongoTemplate.createCollection(Tenant.class);
        }
    }

    /**
     * Drops the {@link Tenant} collection if the collection does already exists
     */
    public void dropTenantCollection() {
        if (mongoTemplate.collectionExists(Tenant.class)) {
            mongoTemplate.dropCollection(Tenant.class);
        }
    }
}
