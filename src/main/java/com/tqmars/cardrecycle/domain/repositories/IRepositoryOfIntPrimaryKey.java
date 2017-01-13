package com.tqmars.cardrecycle.domain.repositories;

import com.tqmars.cardrecycle.domain.entities.IEntity;

/**
 * Created by jjh on 1/11/17.
 */
public interface IRepositoryOfIntPrimaryKey<TEntity extends IEntity<Integer>> extends IRepository<TEntity,Integer>{
}
