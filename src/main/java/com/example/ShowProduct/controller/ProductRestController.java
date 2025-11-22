package com.example.ShowProduct.controller;

import com.example.ShowProduct.model.Product;
import com.example.ShowProduct.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ShowProduct.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/rest") // 設置一個基底路徑("/api/stream-exercises")
public class ProductRestController {

    private final ProductService productService;

    // 建構子注入
    public ProductRestController(ProductService productService) {
        this.productService = productService;

        // --- 階段二核心變動：在 Controller 構造完成後，載入初始測試資料 ---
        productService.loadInitialData();
    }

    // 練習 1: 取得所有產品的 DTO 列表 (JSON API)
    @GetMapping("/products-all-dto")
    public List<ProductDto> getAllProductsDto() {
        return productService.findAllProducts();
    }

    // 練習 2: 查找在庫的書籍列表 (JSON API)
    @GetMapping("/products/books/instock")
    public List<ProductDto> getInStockBooks() {
        return productService.findInStockBooks();
    }

    // 練習 3 & 4: 根據 ID 查找產品，若無則返回 404 (JSON API - Optional 範例)
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            // 使用 findProductByIdOrThrow，如果找不到會拋出 NoSuchElementException
            Product product = productService.findProductByIdOrThrow(id);
            return ResponseEntity.ok(product);
        } catch (NoSuchElementException e) {
            // 捕獲例外，返回 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

}
