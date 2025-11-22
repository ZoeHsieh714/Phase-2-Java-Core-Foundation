package com.example.ShowProduct.dto;

import com.example.ShowProduct.model.Product;

//這是API要回傳給前端的資料結構 APIがフロントエンドに送るデータ構造
public class ProductDto {
    private Long productId;
    private String productName;
    private String category;
    private double price; // 使用小寫 double 以匹配 Entity

    // 確保有這個構造子，類型和順序必須完全匹配 (Long, String, String, double)
    public ProductDto(Long productId, String productName, String productCategory, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.category = productCategory;
        this.price = productPrice;
    }

    public Long getProductId() {return productId;}
    public void setProductId(Long productId) {this.productId = productId;}

    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

}
