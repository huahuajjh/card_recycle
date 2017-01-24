package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Order;
import com.tqmars.cardrecycle.domain.repositories.IOrderReposiroty;

/**
 * Created by jjh on 1/23/17.
 */
public class OrderReposiroty extends RepositoryBase<Order,Integer> implements IOrderReposiroty {
    public OrderReposiroty(DbContext _context) {
        super(_context);
    }
}
