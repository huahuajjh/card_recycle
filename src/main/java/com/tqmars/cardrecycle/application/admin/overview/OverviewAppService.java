package com.tqmars.cardrecycle.application.admin.overview;

import com.tqmars.cardrecycle.application.admin.overview.dto.QueryAdminOverviewOutput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.Admin;
import com.tqmars.cardrecycle.domain.entities.data.AdminOverview;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IAdminRepository;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;

import java.util.List;

/**
 * Created by jjh on 2/8/17.
 */
public class OverviewAppService extends BaseAppService implements IOverviewAppService {
    IRepository<AdminOverview,Integer> adminOverviewIRepository;
    IAdminRepository adminRepository;

    public OverviewAppService(IRepository<AdminOverview,Integer> adminOverviewIRepository,
                              IAdminRepository adminRepository) {
        this.adminOverviewIRepository = adminOverviewIRepository;
        this.adminRepository = adminRepository;

        this.adminOverviewIRepository.setEntityClass(AdminOverview.class);
        this.adminRepository.setEntityClass(Admin.class);
    }

    @Override
    public QueryAdminOverviewOutput queryAdminOverview(String token) {
        List<AdminOverview> list = adminOverviewIRepository.getAll();
        if(null == list || list.size() == 0){
            return null;
        }

        AdminOverview ov = list.get(0);
        Admin admin = adminRepository.single("token='"+token+"'");

        QueryAdminOverviewOutput output = AutoMapper.mapping(QueryAdminOverviewOutput.class, ov);
        output.setLastLoginTime(admin.getLastLoginTime());
        adminOverviewIRepository.commit();
        return output;
    }



}
