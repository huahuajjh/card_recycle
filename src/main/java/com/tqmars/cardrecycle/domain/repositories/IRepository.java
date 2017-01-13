package com.tqmars.cardrecycle.domain.repositories;

import com.tqmars.cardrecycle.domain.entities.IEntity;

import java.util.List;

/**
 * Created by jjh on 1/10/17.
 */
public interface IRepository<TEntity extends IEntity<TPrimaryKey>,TPrimaryKey> {
    //query
    TEntity get(TPrimaryKey id);
    TEntity single(String where);
    List<TEntity> getAll();
    List<TEntity> getAllWithCondition(String where);

    //insert
    TEntity insert(TEntity entity);
    TPrimaryKey insertAndGetId(TEntity entity);

    //updateAndReturnEntity
    TEntity updateAndReturnEntity(TEntity entity);
    void update(TEntity entity);

    //delete
    void delete(TEntity entity);
    void deleteById(TPrimaryKey id);
    void deleteWithCondition(String where);

    //aggregates
    int count();
    int countWithCondition(String where);
    boolean isExists(String where);

    //generics utils
    IRepository<TEntity,TPrimaryKey> setEntityClass(Class<TEntity> entityClass);
    Class<TEntity> getEntityClass();

    //transaction
    void closeSession();
    void commit();
    void rollBack();

}
