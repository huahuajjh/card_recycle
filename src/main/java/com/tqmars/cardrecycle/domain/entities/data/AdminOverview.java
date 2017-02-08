package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

/**
 * Created by jjh on 2/8/17.
 */
@Table(name = "v_admin_overview")
public class AdminOverview extends EntityOfIntPrimaryKey {
    @Column(name = "totalOrder")
    private long totalOrder;

    @Column(name = "successCount")
    private long successCount;

    @Column(name = "failCount")
    private long failCount;

    @Column(name = "processingCount")
    private long processingCount;

    @Column(name = "totalUser")
    private long totalUser;

    @Column(name = "withdrawCount")
    private long withdrawCount;

    public long getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(long totalOrder) {
        this.totalOrder = totalOrder;
    }

    public long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(long successCount) {
        this.successCount = successCount;
    }

    public long getFailCount() {
        return failCount;
    }

    public void setFailCount(long failCount) {
        this.failCount = failCount;
    }

    public long getProcessingCount() {
        return processingCount;
    }

    public void setProcessingCount(long processingCount) {
        this.processingCount = processingCount;
    }

    public long getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(long totalUser) {
        this.totalUser = totalUser;
    }

    public long getWithdrawCount() {
        return withdrawCount;
    }

    public void setWithdrawCount(long withdrawCount) {
        this.withdrawCount = withdrawCount;
    }
}
