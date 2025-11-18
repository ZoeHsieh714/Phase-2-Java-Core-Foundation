package com.example.ShowProduct;

import jakarta.annotation.PostConstruct; // Spring Boot 3+ 使用jakarta
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 為了示範，我們同時實作兩個介面
@Component
public class LifecycleDemoBean implements InitializingBean, DisposableBean {

    // 1.依賴 (Dependency)-為了展示DI發生在建構子之後
    private final ProductService productService;

    private String initializationState = "A. Constructor NOT yet run";

    //【階段 1 & 2: 實例化與屬性注入(DI)】
    @Autowired //這裡告訴Spring要注入ProductService
    public LifecycleDemoBean(ProductService productService) {
        System.out.println("[Phase 1/2] Constructor: Bean 實例化 (Instantiation) 完成。");
        //依賴注入(DI)在建構子被呼叫時完成(如果有 @Autowired 建構子)
        this.productService = productService;
        this.initializationState = "B. DI is complete";
    }

    //【階段 3.1: 初始化 - @PostConstruct (推薦)】
    @PostConstruct
    public void setupAfterDI() {
        // 這個方法在 DI 完成後立即執行
        System.out.println("[Phase 3.1] @PostConstruct: 執行初始化設定 (Tracker: " + initializationState + ")");
        // 適合放置：設定 Log 級別、載入靜態資源等
    }

    //【階段 3.2: 初始化-InitializingBean介面】
    @Override
    public void afterPropertiesSet() throws Exception {
        // 這個方法在 @PostConstruct 之後執行
        System.out.println("[Phase 3.2] InitializingBean.afterPropertiesSet: 介面定義的初始化方法。");
    }

    //【階段 5.1: 銷毀-@PreDestroy (推薦)】
    @PreDestroy
    public void cleanupBeforeShutdown() {
        // 應用程式關閉時，第一個被呼叫的銷毀方法
        System.out.println("[Phase 5.1] @PreDestroy: 釋放資源 (例如：關閉連線池、清除快取)。");
    }

    //【階段 5.2: 銷毀-DisposableBean介面】
    @Override
    public void destroy() throws Exception {
        // 這個方法在 @PreDestroy 之後執行
        System.out.println("[Phase 5.2] DisposableBean.destroy: 介面定義的銷毀方法。");
    }

    //【階段 4: 就緒後的使用】
    public void useBean() {
        System.out.println("[Phase 4] Bean Ready: Bean 正在執行正常業務邏輯...");
    }
}