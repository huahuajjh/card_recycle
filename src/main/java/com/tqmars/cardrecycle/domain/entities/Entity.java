package com.tqmars.cardrecycle.domain.entities;

import com.tqmars.cardrecycle.domain.entities.annotation.Column;

/**
 * Created by jjh on 1/11/17.
 */
public abstract class Entity<TPrimaryKey> implements IEntity<TPrimaryKey> {
    @Column(name = "id")
    protected TPrimaryKey pk;

    @Override
    public TPrimaryKey getId() {
        return pk;
    }

    @Override
    public void setId(TPrimaryKey id) {
        this.pk = id;
    }
}
