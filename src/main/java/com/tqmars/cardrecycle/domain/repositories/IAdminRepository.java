package com.tqmars.cardrecycle.domain.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Admin;

/**
 * Created by jjh on 1/14/17.
 */
public interface IAdminRepository extends IRepositoryOfIntPrimaryKey<Admin> {
    String login(Admin admin);
    void logout(Admin admin);
    boolean auth(String token);
}
