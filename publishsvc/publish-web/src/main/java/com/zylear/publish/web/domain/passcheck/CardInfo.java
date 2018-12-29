package com.zylear.publish.web.domain.passcheck;

import java.util.Date;

public class CardInfo {

    public static final int PASS_CHECK_VIP_CARD = 0;

    public static final int PLUGIN_VIP_CARD = 100;

    public static final int BOTH_CARD = 200;

    public static void main(String[] args) {
        System.out.println(70 - 70 % 100);
        System.out.println(170 - 170 % 100);
        System.out.println(201 - 201 % 100);
    }

    private Integer id;

    private String cardNumber;

    private String cardPassword;

    private Integer months;

    private Boolean isUsed;

    private String useAccount;

    private Boolean isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getUseAccount() {
        return useAccount;
    }

    public void setUseAccount(String useAccount) {
        this.useAccount = useAccount;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}