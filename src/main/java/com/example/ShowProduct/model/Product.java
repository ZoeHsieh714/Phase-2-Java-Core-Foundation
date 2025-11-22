package com.example.ShowProduct.model;

import jakarta.persistence.Entity;          // <-- 必須使用 jakarta
import jakarta.persistence.GeneratedValue;    // <-- 必須使用 jakarta
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;              // <-- 必須使用 jakarta
import jakarta.persistence.Table;           // <-- 必須使用 jakarta
// ... 確保沒有 javax.persistence 的導入

@Entity // <-- 必須有這個註解
@Table(name = "product_info")
public class Product {

    @Id // <-- 必須標記主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;        //商品名稱　商品名称
    private String category;    //商品類別　カテゴリー
    private double price;       //價格　価格
    private int stock;          //庫存　在庫

    // JPA 要求必須有一個無參數的構造函數
    public Product() {
    }

    public Product(Long id, String name, String category, double price, int stock){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    //Steam API 會用到這些getters，非常重要***　Steam APIが利用するgetters,とても重要
    public Long getId() {return id;}
    public String getName(){return name;};
    public String getCategory(){return category;}
    public double getPrice(){return price;}
    public int getStock(){return stock;}

    }

