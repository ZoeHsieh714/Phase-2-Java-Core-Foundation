package com.example.ShowProduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.ShowProduct.service.ProductService;

// 建構子注入 (Constructor Injection)
@Controller
@RequestMapping("/web") //將網頁相關的需求放在/web路徑下
public class ProductWebController {
    private final ProductService productService;

    // 建構子注入
    // 此 Controller 不需要載入資料，因為 ProductRestController 已經載入過一次
    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 【模組 3 範例】顯示產品列表頁面
     * 將 Service 層取得的 JPA 數據傳遞給 Thymeleaf
     */
    @GetMapping("/products")
    public String showProductsPage(Model model) {
        // 1. 完整商品列表 (使用 JPA 數據)
        model.addAttribute("allProducts", productService.findAllProducts());

        // 2. 過濾後的書籍列表 (使用 JPA 數據)
        model.addAttribute("inStockBooks", productService.findInStockBooks());

        // 導向 templates/products.html 模板
        return "products";
    }
}
