package com.digua.kotlinapp.main.bean;

/**
 *
 *
 * @author RunningDigua
 * @date 2021/3/26
 */
public class GoodsInfo {

    private String productName;

    private String productId;

    private float price;

    private int status;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
