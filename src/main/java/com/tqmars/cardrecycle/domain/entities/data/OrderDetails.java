package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jjh on 1/18/17.
 */
@Table(name = "v_order")
public class OrderDetails extends EntityOfIntPrimaryKey {

    @Column(name = "id")
    private Integer id;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "order_number")
    private String orderNum;

    @Column(name = "order_status")
    private int orderStatus;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @Column(name = "card_number")
    private String cardNum;

    @Column(name = "id_card_num")
    private String idCardNum;

    @Column(name = "support_amount")
    private BigDecimal cardAmount;

    @Column(name = "sale_ratio")
    private float saleRatio;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    public float getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(float saleRatio) {
        this.saleRatio = saleRatio;
    }

    public enum OrderStatus {
        FAIL(2, "失败"),
        PROCESSING(0, "处理中"),
        SUCCESS(1, "成功");

        private int status;
        private String statusName;

        private OrderStatus(int _status, String _statusName) {
            status = _status;
            statusName = _statusName;
        }
    }
}
