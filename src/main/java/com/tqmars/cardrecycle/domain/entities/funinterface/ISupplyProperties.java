package com.tqmars.cardrecycle.domain.entities.funinterface;

/**
 * Created by jjh on 1/12/17.
 */
@FunctionalInterface
public interface ISupplyProperties<TEntity> {
    Object property(TEntity entity);

}