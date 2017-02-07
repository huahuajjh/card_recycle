package com.tqmars.cardrecycle.application.withdraw;

import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.withdraw.dto.ApplyWithdrawInput;
import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordInput;
import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordOutput;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.entities.data.Wallet;
import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecord;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;
import com.tqmars.cardrecycle.domain.repositories.IWalletRepository;
import com.tqmars.cardrecycle.domain.repositories.IWithdrawRecordRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jjh on 1/22/17.
 */
public class WithdrawAppService extends BaseAppService implements IWithdrawAppService{
    private IWithdrawRecordRepository _withdrawRecordRepository;
    private IUserRepository _userRepository;
    private IWalletRepository _walletRepository;

    public WithdrawAppService(IWithdrawRecordRepository _withdrawRecordRepository,
                              IUserRepository _userRepository,
                              IWalletRepository _walletRepository) {
        this._withdrawRecordRepository = _withdrawRecordRepository;
        this._userRepository = _userRepository;
        this._walletRepository = _walletRepository;

        this._withdrawRecordRepository.setEntityClass(WithdrawRecord.class);
        this._userRepository.setEntityClass(User.class);
        this._walletRepository.setEntityClass(Wallet.class);
    }

    @Override
    public String queryWithdrawRecord(QueryWithdrawRecordInput input) {
        StringBuilder sb = new StringBuilder("tb_user_id="+input.getUserId());

        if(null != input.getProcessStatus()){
            sb.append(" and process_status="+input.getProcessStatus());
        }else if(null != input.getFrom() && null != input.getTo()){
            sb.append(" and apply_time between '").append(input.getFrom()+"' and '")
                    .append(input.getTo()+"'");
        }

        sb.append(" limit "+(input.getIndex()-1)*input.getCount()+","+input.getCount());

        List<WithdrawRecord> list = _withdrawRecordRepository.getAllWithCondition(sb.toString());
        int count = _withdrawRecordRepository.countWithCondition("tb_user_id="+input.getUserId());
        _walletRepository.commit();
        return toJsonWithPageFormatter(AutoMapper.mapping(QueryWithdrawRecordOutput.class,list),
                "success", Code.SUCCESS,count);
    }

    @Override
    public String applyWithdraw(ApplyWithdrawInput input) {
        User user = _userRepository.get(input.getUserId());
        if(null == user){
            _userRepository.commit();
            return toJsonWithFormatter(null,"未查询到用户",Code.FAIL);
        }

        if(null == user.getIdCardNum() || null == user.getName()){
            _userRepository.commit();
            return toJsonWithFormatter(null,"未实名认证",Code.FAIL);
        }

        boolean r = _userRepository.isExists("withdraw_pwd='"+ Md5.md5WithSalt(input.getWithdrawPwd())+"'");
        if(!r){
            _withdrawRecordRepository.commit();
            return toJsonWithFormatter(null,"提现密码错误",Code.FAIL);
        }

        Wallet wallet = _walletRepository.single("tb_user_id="+input.getUserId());

        if(null == wallet || wallet.getBalance().compareTo(input.getWithdrawAmount()) < 0){
            _withdrawRecordRepository.commit();
            return toJsonWithFormatter(null,"余额不足",Code.FAIL);
        }

        WithdrawRecord record = new WithdrawRecord();
        record.userWithdraw(input.getBankName(),input.getUserId(),input.getBankId(),input.getWithdrawAmount(),input.getCardNum());

        //更新钱包,若提现小于500,则扣除手续费1元,大于等于500不需要手续费
        if(input.getWithdrawAmount().compareTo(new BigDecimal("500")) < 0){
            wallet.setBalance(wallet.getBalance().subtract(input.getWithdrawAmount()).subtract(new BigDecimal("1")));
        }else{
            wallet.setBalance(wallet.getBalance().subtract(input.getWithdrawAmount()));
        }

        _withdrawRecordRepository.insert(record);
        _walletRepository.update(wallet);
        _withdrawRecordRepository.commit();
        return toJsonWithFormatter(wallet.getBalance(),"提现申请成功,等待后台审批",Code.SUCCESS);
    }
}
