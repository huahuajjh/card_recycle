package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_order")
public class Order extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "tb_user_id")
    private Integer uesrId;

    @Column(name = "tb_rechargeable_card_type_id")
    private Integer cardTypeId;

    @Column(name = "tb_rechargeable_card_type_item_id")
    private Integer cardTypeItemId;

    @Column(name = "order_time")
    private Timestamp orderTime;

    @Column(name = "order_number")
    private String orderNum;

    @Column(name = "order_status")
    private int orderStatus;

    @Column(name = "process_time")
    private Timestamp processTime;

    @Column(name = "rechargeable_card_number")
    private String cardNum;

    @Column(name = "actual_amount")
    private BigDecimal actualAmount;

    @Column(name = "tb_rechargeable_card_id")
    private Integer cardId;

    @Column(name = "complete_time")
    private Timestamp completeTime;

    @Column(name = "third_order_no")
    private String thirdOrderNo;

    @Column(name = "third_msg")
    private String thirdMsg;

    public String getThirdMsg() {
        return thirdMsg;
    }

    public void setThirdMsg(String thirdMsg) {
        this.thirdMsg = thirdMsg;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public void setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUesrId() {
        return uesrId;
    }

    public void setUesrId(Integer uesrId) {
        this.uesrId = uesrId;
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public Integer getCardTypeItemId() {
        return cardTypeItemId;
    }

    public void setCardTypeItemId(Integer cardTypeItemId) {
        this.cardTypeItemId = cardTypeItemId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Timestamp processTime) {
        this.processTime = processTime;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Timestamp getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Timestamp completeTime) {
        this.completeTime = completeTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uesrId=" + uesrId +
                ", cardTypeId=" + cardTypeId +
                ", cardTypeItemId=" + cardTypeItemId +
                ", orderTime=" + orderTime +
                ", orderNum='" + orderNum + '\'' +
                ", orderStatus=" + orderStatus +
                ", processTime=" + processTime +
                ", cardNum='" + cardNum + '\'' +
                ", actualAmount=" + actualAmount +
                ", cardId=" + cardId +
                ", completeTime=" + completeTime +
                '}';
    }
}
