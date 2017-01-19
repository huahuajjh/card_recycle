package com.tqmars.cardrecycle.domain.services.withdraw;

import com.tqmars.cardrecycle.domain.entities.data.Wallet;
import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecord;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.domain.repositories.IWalletRepository;

/**
 * Created by jjh on 1/19/17.
 */
public class WithdrawDomainService implements IWithdrawDomainService {
    private IWalletRepository _walletRepository;
    private IRepository<WithdrawRecord,Integer> _withdrawRecordRepository;

    public WithdrawDomainService(IWalletRepository _walletRepository, IRepository<WithdrawRecord, Integer> _withdrawRecordRepository) {
        this._walletRepository = _walletRepository;
        this._withdrawRecordRepository = _withdrawRecordRepository;

        this._walletRepository.setEntityClass(Wallet.class);
        this._withdrawRecordRepository.setEntityClass(WithdrawRecord.class);
    }

    @Override
    public String dealwithWithdraw(WithdrawRecord record) {
        Wallet wallet = _walletRepository.single("tb_uesr_id="+record.getUserId());

        try {
            wallet.withdraw(record.getWithdrawAmount());
            record.withdraw();

            _withdrawRecordRepository.update(record);
            _walletRepository.update(wallet);

            _withdrawRecordRepository.commit();

            return "success";
        } catch (Exception e) {
            return "fail";
        }finally {
            _withdrawRecordRepository.commit();
        }
    }
}
