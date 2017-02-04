package com.tqmars.cardrecycle.application.wallet;

import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.Wallet;
import com.tqmars.cardrecycle.domain.repositories.IWalletRepository;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

/**
 * Created by jjh on 2/4/17.
 */
public class WalletAppService extends BaseAppService implements IWalletAppService{
    private IWalletRepository _walletRepository;

    public WalletAppService(IWalletRepository _walletRepository) {
        this._walletRepository = _walletRepository;

        this._walletRepository.setEntityClass(Wallet.class);
    }

    @Override
    public String getBalance(Integer userId) {
        Wallet wallet = _walletRepository.single("tb_user_id="+userId);
        _walletRepository.commit();
        return toJsonWithFormatter(wallet.getBalance(),"success", Code.SUCCESS);
    }
}
