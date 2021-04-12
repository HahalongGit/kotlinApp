package com.digua.login.bean;

/**
 * 登录后的用户信息
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class UserInfo {

    private String userName;

    private String userId;

    private String accountId;

    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
