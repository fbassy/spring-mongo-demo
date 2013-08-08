package com.exoplatform.cloudworkspaces;

import com.exoplatform.cloudworkspaces.domain.ServiceLevels;
import com.exoplatform.cloudworkspaces.domain.Tenant;
import com.exoplatform.cloudworkspaces.repositories.TenantRepository;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TenantRepositoryTest {
  static Logger logger = LoggerFactory.getLogger(TenantRepositoryTest.class);

  @Autowired
  TenantRepository tenantRepository;
  @Autowired
  MongoTemplate mongoTemplate;

  @Before
  public void setUp() {
    // create the collection
  }

  @After
  public void tearDown() {
    // cleanup the collection
    this.tenantRepository.deleteAll();
  }

  @Test
  public void insertTenants() {

    for (int i = 0; i < 10; i++) {
      int number = (int) (Math.random() * 10000);
      Tenant p = new Tenant("tenant-" + number, number, number, new ServiceLevels("tenant-"+number,1));
      this.mongoTemplate.insert(p);
    }

    long nb = mongoTemplate.getCollection("tenants").count();
    Assert.assertEquals("Wrong number of inserted tenants",10,nb);
  }

  @Test
  public void noTenants() {

    long nb = mongoTemplate.getCollection("tenants").count();
    Assert.assertEquals("We should have no tenant in the collection tenants",0,nb);
  }

  @Test
  public void crud() {
    // Insert a tenant
    Tenant p = new Tenant("Mock-tenant");
    mongoTemplate.insert(p);

    // Find the tenant
    Tenant fTenant = tenantRepository.findByName("Mock-tenant");
    Assert.assertNotNull(fTenant);

    // Delete the tenant
    tenantRepository.delete("Mock-tenant");
    Tenant dTenant =  tenantRepository.findByName("Mock-tenant");

    Assert.assertNull(dTenant);

  }
}
