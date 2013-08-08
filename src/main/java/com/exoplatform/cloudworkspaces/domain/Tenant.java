package com.exoplatform.cloudworkspaces.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tenants")
@TypeAlias("Tenants")
public class Tenant {

    @Id
    private String name;
    private int userLimit;
    private int storageLimit;
    private ServiceLevels serviceLevel;

    @PersistenceConstructor
    public Tenant(String name, int userLimit, int storageLimit, ServiceLevels serviceLevel) {
        this.name = name;
        this.userLimit = userLimit;
        this.storageLimit = storageLimit;
        this.serviceLevel = serviceLevel;
    }

    public ServiceLevels getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(ServiceLevels serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Tenant(String name) {

        this.name = name;
        this.userLimit = 5;
        this.storageLimit = 5;
        this.serviceLevel = new ServiceLevels(name, 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    public int getStorageLimit() {
        return storageLimit;
    }

    public void setStorageLimit(int storageLimit) {
        this.storageLimit = storageLimit;
    }

    @Override
    public String toString() {
        //    return JSON.serialize(this);
        return "Tenant [name=" + name + ", userLimit=" + userLimit + ", storageLimit=" + storageLimit + ", serviceLevel=" + serviceLevel + "]";
    }

}

