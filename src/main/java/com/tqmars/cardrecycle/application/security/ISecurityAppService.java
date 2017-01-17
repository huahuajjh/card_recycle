package com.tqmars.cardrecycle.application.security;

import com.tqmars.cardrecycle.application.security.dto.ChangeWithdrawPwdInput;
import com.tqmars.cardrecycle.application.security.dto.RealNameAuthInput;

/**
 * Created by jjh on 1/16/17.
 */
public interface ISecurityAppService {
    boolean changeWithdrawPwd(ChangeWithdrawPwdInput input);
    boolean realNameAuth(RealNameAuthInput input);

}
