package com.example.ShowProduct.service;

import com.example.ShowProduct.model.Product;
import com.example.ShowProduct.dto.ProductDto;
import com.example.ShowProduct.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {
    // 移除靜態資料 (static List<Product> products)

    // 1. 注入 Repository 介面
    private final ProductRepository productRepository;

    // 建構子注入 (Constructor Injection)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 輔助方法：將 Entity 轉換為 DTO (保留階段一的 DTO 轉換邏輯)
    private ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice()
        );
    }

    // 練習 1: 取得所有產品的 DTO 列表 (替換為資料庫 findAll)  全ての商品を取得し、ProductDtoリストに変換
    public List<ProductDto> findAllProducts() {
        // 從資料庫取得所有 Product 實體
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 練習 2: 查找在庫的書籍 (使用 Stream 過濾，但數據來自資料庫)  商品カテゴリーが"Book"、かつ在庫ある商品を取得しDTOに変換
    public List<ProductDto> findInStockBooks() {
        return productRepository.findAll().stream() // 從資料庫取得
                .filter(p -> "Book".equals(p.getCategory()))
                .filter(p -> p.getStock() > 0)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 練習 3: 根據 ID 查找產品 (替換為資料庫 findById)  商品を“ID”によって検索
    public Optional<Product> findProductById(Long id) {
        // 使用 JpaRepository 的 findById，返回 Optional
        return productRepository.findById(id);
    }

    // 練習 4: 根據 ID 查找產品，若無則拋出例外 (orElseThrow 邏輯保留)
    public Product findProductByIdOrThrow(Long id) {
        return findProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    // --- 新增：啟動時載入測試資料 ---
    // 雖然這段程式碼不屬於 ProductService 的職責，但為了測試方便，我們在 service 裡新增測試資料

    public void loadInitialData() {
        if (productRepository.count() == 0) {
            System.out.println("--- [JPA] Loading initial mock data into H2 ---");
            productRepository.saveAll(List.of(
                    new Product(1L,"Java Core", "Book", 49.99, 10),
                    new Product(2L,"Spring in Action", "Book", 59.99, 5),
                    new Product(3L,"Wireless Mouse", "Electronics", 25.50, 20),
                    new Product(4L,"USB Cable", "Accessory", 5.00, 50)
            ));
            System.out.println("--- [JPA] Initial data loaded: " + productRepository.count() + " records ---");
        }
    }

}