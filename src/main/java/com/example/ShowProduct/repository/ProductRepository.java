package com.example.ShowProduct.repository;

import com.example.ShowProduct.model.Product; // 確保路徑指向您的 Product 實體
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 產品數據存取層 (Product Data Access Layer)
 * 繼承 JpaRepository<Entity類型, 主鍵類型> 即可獲得完整的 CRUD 功能
 */
@Repository // 雖然繼承 JpaRepository 會自動被識別，但加上這個註解更清晰
public interface ProductRepository extends JpaRepository<Product, Long> {

    // JpaRepository<Product, Long> 說明：
    // 1. Product: 此 Repository 操作的 Entity 類型
    // 2. Long: 此 Entity 的主鍵 (Primary Key) 類型

    // 此處不需要編寫任何方法實作，Spring Data JPA 會自動生成實現。
}