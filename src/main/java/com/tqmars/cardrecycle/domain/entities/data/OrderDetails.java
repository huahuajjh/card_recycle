package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by jjh on 1/18/17.
 */
@Table(name = "v_order")
public class OrderDetails extends EntityOfIntPrimaryKey {

    @Column(name = "id")
    private Integer id;

    @Column(name = "account")
    private String account;

    @Column(name = "tb_user_id")
    private Integer userId;

    @Column(name = "order_time")
    private Timestamp orderTime;

    @Column(name = "order_number")
    private String orderNum;

    @Column(name = "actual_amount")
    private BigDecimal actualAmount;

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

    @Column(name = "cardTypeName")
    private String cardTypeName;

    @Column(name = "tb_rechargeable_card_type_id")
    private Integer cardTypeId;

    @Column(name = "tb_rechargeable_card_id")
    private Integer cardId;

    @Column(name = "tb_rechargeable_card_type_item_id")
    private Integer cardTypeItemId;

    @Column(name = "process_time")
    private Timestamp processTime;

    @Column(name = "third_msg")
    private String thirdMsg;

    public String getThirdMsg() {
        return thirdMsg;
    }

    public void setThirdMsg(String thirdMsg) {
        this.thirdMsg = thirdMsg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Timestamp getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Timestamp processTime) {
        this.processTime = processTime;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCardTypeItemId() {
        return cardTypeItemId;
    }

    public void setCardTypeItemId(Integer cardTypeItemId) {
        this.cardTypeItemId = cardTypeItemId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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
