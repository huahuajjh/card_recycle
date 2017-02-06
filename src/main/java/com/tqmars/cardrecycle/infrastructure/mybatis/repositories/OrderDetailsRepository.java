package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.OrderDetails;
import com.tqmars.cardrecycle.domain.repositories.IOrderDetailsRepository;

/**
 * Created by jjh on 2/6/17.
 */
public class OrderDetailsRepository extends RepositoryBase<OrderDetails,Integer> implements IOrderDetailsRepository {
    public OrderDetailsRepository(DbContext _context) {
        super(_context);
    }
}
