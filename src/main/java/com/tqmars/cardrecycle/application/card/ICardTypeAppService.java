package com.tqmars.cardrecycle.application.card;

import com.tqmars.cardrecycle.application.admin.card.dto.QueryCardTypeOutput;
import com.tqmars.cardrecycle.application.card.dto.QueryCardItemOutput;

import java.util.List;

/**
 * Created by jjh on 1/21/17.
 */
public interface ICardTypeAppService {
    List<QueryCardTypeOutput> queryCardTypeList();
    List<QueryCardItemOutput> queryCardItem(Integer cardTypeId);
}
