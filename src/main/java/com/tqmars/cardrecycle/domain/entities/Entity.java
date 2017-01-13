package com.tqmars.cardrecycle.domain.entities;

import com.tqmars.cardrecycle.domain.entities.annotation.Column;

/**
 * Created by jjh on 1/11/17.
 */
public abstract class Entity<TPrimaryKey> implements IEntity<TPrimaryKey> {
    @Column(name = "id")
    protected TPrimaryKey id;
    protected String tableName;

    public Entity(String _tableName){
        this.tableName = _tableName;
    }

    @Override
    public String getTableName(){
        return this.tableName;
    }

    @Override
    public TPrimaryKey getId() {
        return id;
    }

    @Override
    public void setId(TPrimaryKey id) {
        this.id = id;
    }
}
