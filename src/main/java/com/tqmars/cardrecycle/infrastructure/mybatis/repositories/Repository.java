package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.IEntity;
import com.tqmars.cardrecycle.domain.repositories.IRepositoryOfIntPrimaryKey;

/**
 * Created by jjh on 1/11/17.
 */
public class Repository<TEntity extends IEntity<Integer>> extends RepositoryBase<TEntity,Integer> implements IRepositoryOfIntPrimaryKey<TEntity> {
    public Repository(DbContext _context) {
        super(_context);
    }
}
