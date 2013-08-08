package com.exoplatform.cloudworkspaces.domain;

import com.mongodb.util.JSON;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Tenant implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    private String name;
    private int userLimit;
    private int storageLimit;

    @PersistenceConstructor
    public Tenant(String name, int userLimit, int storageLimit) {
        this.name = name;
        this.userLimit = userLimit;
        this.storageLimit = storageLimit;
    }

    public Tenant(String name) {
        this.name = name;
        this.userLimit = 5;
        this.storageLimit = 5;
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
        //return JSON.serialize(this);
        return "Person [name=" + name + ", userLimit=" + userLimit + ", storageLimit=" + storageLimit + "]";
    }

}

