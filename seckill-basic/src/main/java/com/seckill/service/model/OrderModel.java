package com.seckill.service.model;

import java.math.BigDecimal;

/**
 * 用户下单交易模型
 */
public class OrderModel {

    //16位(前8:日期,中间6自增序列,后2:分库分表位)
    private String id;

    //下单用户id
    private Integer userId;

    //购买商品id
    private Integer itemId;

    //购买商品单价
    private BigDecimal itemPrice;

    //购买数量
    private Integer amount;

    //购买总额
    private BigDecimal orderPrice;

    //活动id (若非空，则表示是以秒杀商品方式下单)
    private Integer promoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}
