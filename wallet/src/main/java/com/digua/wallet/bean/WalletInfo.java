package com.digua.wallet.bean;

import java.util.List;

/**
 * 钱包信息数据
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class WalletInfo {

    /**
     * 钱包id
     */
    private String walletId;

    /**
     * 钱包余额
     */
    private double balance;

    /**
     * 钱包绑定的银行卡
     */
    List<BackCard> backCardList;


    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<BackCard> getBackCardList() {
        return backCardList;
    }

    public void setBackCardList(List<BackCard> backCardList) {
        this.backCardList = backCardList;
    }

    /**
     * 银行卡信息
     */
    public static class BackCard{

        private String cardName;

        private String banckName;

        private String backCardNum;

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getBanckName() {
            return banckName;
        }

        public void setBanckName(String banckName) {
            this.banckName = banckName;
        }

        public String getBackCardNum() {
            return backCardNum;
        }

        public void setBackCardNum(String backCardNum) {
            this.backCardNum = backCardNum;
        }
    }

}
