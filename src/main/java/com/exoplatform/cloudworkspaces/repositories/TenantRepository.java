package com.exoplatform.cloudworkspaces.repositories;

import com.exoplatform.cloudworkspaces.domain.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface TenantRepository extends MongoRepository<Tenant, String> {

  Tenant findByName(String name);
}
