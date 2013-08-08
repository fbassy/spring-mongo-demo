package com.exoplatform.cloudworkspaces.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Storage {


    @Id
    private String idTenant;

    @Id
    private Date date;

    private int database;
    private int indexes;
    private int backup;

    @PersistenceConstructor
    public Storage(String idTenant, Date date, int database, int indexes, int backup) {
        this.idTenant = idTenant;
        this.date = date;
        this.database = database;
        this.indexes = indexes;
        this.backup = backup;
    }

    public String getIdTenant() {
        return idTenant;
    }

    public void setIdTenant(String idTenant) {
        this.idTenant = idTenant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getIndexes() {
        return indexes;
    }

    public void setIndexes(int indexes) {
        this.indexes = indexes;
    }

    public int getBackup() {
        return backup;
    }

    public void setBackup(int backup) {
        this.backup = backup;
    }

    @Override
    public String toString() {
        //    return JSON.serialize(this);
        return "Storage [idTenant=" + idTenant + ", date=" + date + ", database=" + database + ", indexes=" + indexes + ", backup=" + backup + "]";
    }
}
