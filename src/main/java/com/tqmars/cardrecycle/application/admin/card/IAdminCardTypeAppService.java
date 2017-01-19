package com.tqmars.cardrecycle.application.admin.card;

import com.tqmars.cardrecycle.application.admin.card.dto.*;

import java.util.List;

/**
 * Created by jjh on 1/16/17.
 */
public interface IAdminCardTypeAppService {
    void addCardType(AddCardTypeInput input);
    void modifyCardType(ModifyCardTypeInput input);
    void delCardType(int id);
    List<QueryCardTypeOutput> queryCardType(QueryCardTypeInput input);
    void modifyCardTypeItem(ModifyCardTypeItemInput input);
    void delCardTypeItem(Integer id);
    List<QueryCardTypeItemOutput> queryCardTypeItem(Integer input);
    void addCardTypeItem(AddCardTypeItemInput input);

}
