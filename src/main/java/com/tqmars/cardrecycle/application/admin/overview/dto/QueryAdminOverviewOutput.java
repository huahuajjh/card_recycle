package com.tqmars.cardrecycle.application.admin.overview.dto;

import java.sql.Timestamp;

/**
 * Created by jjh on 2/8/17.
 */
public class QueryAdminOverviewOutput {
    private long totalOrder;
    private long successCount;
    private long failCount;
    private long processingCount;
    private long totalUser;
    private long withdrawCount;
    private Timestamp lastLoginTime;

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

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

    @Override
    public String toString() {
        return "QueryAdminOverviewOutput{" +
                "totalOrder=" + totalOrder +
                ", successCount=" + successCount +
                ", failCount=" + failCount +
                ", processingCount=" + processingCount +
                ", totalUser=" + totalUser +
                ", withdrawCount=" + withdrawCount +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
