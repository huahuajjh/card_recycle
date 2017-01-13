package com.tqmars.cardrecycle.domain.entities;

import com.tqmars.cardrecycle.domain.entities.funinterface.ISupplyProperties;

/**
 * Created by jjh on 1/12/17.
 */
public interface IEntity2Table<TEntity> {
    String getTableName();
    Object property(ISupplyProperties<TEntity> entity);

}