package com.tqmars.cardrecycle.domain.repositories;

import com.tqmars.cardrecycle.domain.entities.data.User;

/**
 * Created by jjh on 1/14/17.
 */
public interface IUserRepository extends IRepositoryOfIntPrimaryKey<User> {
    String login(User user);

    void logout(User user);

}
