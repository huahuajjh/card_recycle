package com.tqmars.cardrecycle.domain.services.withdraw;

import com.tqmars.cardrecycle.domain.entities.data.Wallet;
import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecord;
import com.tqmars.cardrecycle.domain.repositories.IWalletRepository;
import com.tqmars.cardrecycle.domain.repositories.IWithdrawRecordRepository;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/19/17.
 */
public class WithdrawDomainService implements IWithdrawDomainService {
    private IWalletRepository _walletRepository;
    private IWithdrawRecordRepository _withdrawRecordRepository;

    public WithdrawDomainService(IWalletRepository _walletRepository, IWithdrawRecordRepository _withdrawRecordRepository) {
        this._walletRepository = _walletRepository;
        this._withdrawRecordRepository = _withdrawRecordRepository;

        this._walletRepository.setEntityClass(Wallet.class);
        this._withdrawRecordRepository.setEntityClass(WithdrawRecord.class);
    }

    @Override
    public void dealwithWithdraw(WithdrawRecord record) {
        Wallet wallet = _walletRepository.single("tb_user_id=" + record.getUserId());

        switch (record.getProcessStatus()) {
            case 1://处理成功,已经打款
                record.setProcessStatus(1);
                record.setMsg("已打款");
                break;
            case 2://处理失败,或者放弃打款,则恢复用户钱包减去的提现金额
                if(record.getWithdrawAmount().compareTo(new BigDecimal("500")) < 0){
                    wallet.setBalance(wallet.getBalance().add(record.getWithdrawAmount()).add(new BigDecimal("1")));
                }else {
                    wallet.setBalance(wallet.getBalance().add(record.getWithdrawAmount()));
                }

                record.setMsg("失败");
                _walletRepository.update(wallet);
                break;
            default:
                break;
        }

        _withdrawRecordRepository.update(record);
        _withdrawRecordRepository.commit();
    }
}
