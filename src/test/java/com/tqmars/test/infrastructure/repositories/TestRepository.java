package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardType;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jjh on 1/11/17.
 */
public class TestRepository {
    private IRepository<RechargeableCardType, Integer> _r;
    private IRepository<User, Integer> _u;

    @Before
    public void repositoryInit() {
        _r = ServiceLocator.getInstance().getService("RepositoryBase", IRepository.class).setEntityClass(RechargeableCardType.class);
        _u = ServiceLocator.getInstance().getService("RepositoryBase", IRepository.class).setEntityClass(User.class);
    }

    @Test
    public void testSelect(){
//        IRepository<RechargeableCardType, Integer> _r = ServiceLocator.getInstance().getService("RepositoryBase", IRepository.class).setEntityClass(RechargeableCardType.class);
        System.out.println(_r.get(1));
        System.out.println(_u.get(1));
    }

}
