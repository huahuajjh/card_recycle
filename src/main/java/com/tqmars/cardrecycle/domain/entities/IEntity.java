package com.tqmars.cardrecycle.domain.entities;

/**
 * Created by jjh on 1/11/17.
 */
public interface IEntity<TPrimaryKey> {
    TPrimaryKey getId();
    void setId(TPrimaryKey id);

    String getTableName();
    


}
