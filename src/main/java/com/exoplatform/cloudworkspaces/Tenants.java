package com.exoplatform.cloudworkspaces;

import org.slf4j.Logger;
import com.exoplatform.cloudworkspaces.domain.Tenant;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

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

        Iterator<Tenant> TenantIterator = results.iterator();
        while (TenantIterator.hasNext()) {
            Tenant nextTenant = TenantIterator.next();
            logger.info(nextTenant.toString());
        }

       // logger.info("Results: {}", results.toString());
    }


    public void insertMockTenant() {
        Tenant p = new Tenant("Mock-tenant");
        mongoTemplate.insert(p);
    }

    public Tenant findTenantByName(String name) {
        Query query = new Query(where("name").is(name));
        // Execute the query and find one matching entry
        Tenant sampleTenant = mongoTemplate.findOne(query, Tenant.class);

        return sampleTenant;
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
     * Create a {@link Tenant} collection if the collection does not already exists
     */
    public Tenant deleteTenant(String name) {
        Query query = new Query(where("name").is(name));
        // Execute the query and find one matching entry
        mongoTemplate.findAndRemove(query, Tenant.class);
        return mongoTemplate.findAndRemove(query, Tenant.class);
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
