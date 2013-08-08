package com.exoplatform.cloudworkspaces.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ServiceLevels {

    @Id
    private String idTenant;

    private int levels;

    @PersistenceConstructor
    public ServiceLevels(String idTenant, int levels) {
        this.idTenant = idTenant;
        this.levels = levels;
    }

    public String getIdTenant() {
        return idTenant;
    }

    public void setIdTenant(String idTenant) {
        this.idTenant = idTenant;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    @Override
    public String toString() {
        return "ServiceLevels [idTenant=" + idTenant + ", levels=" + levels + "]";
    }
}
