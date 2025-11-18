package com.example.ShowProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; //必須引入@Bean

@SpringBootApplication
public class ShowProductApplication {

	public static void main(String[] args) {
        // --- 1.執行Spring Boot應用 --- (啟動應用程式，這會觸發 Phase 1, 2, 3)
        SpringApplication.run(ShowProductApplication.class, args);
        // 當應用程式被手動停止 (例如按 Ctrl+C)，會觸發 Phase 5

        // --- 2.獨立運行IoC模擬(在Spring容器執行後，手動觸發) ---
        try {
            System.out.println("\n==============================================");
            System.out.println("== [DEMO]反射與註解：模擬IoC容器掃描與實例化 ==");
            System.out.println("==============================================");
            SimpleIocContainer.runSimulation(ProductServiceStub.class);
            System.out.println("==============================================\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 【Bean 生命週期 Phase 4: Ready for Use 的展示】
     * @Bean 註解會讓Spring建立這個方法回傳的物件。
     * 使用 CommandLineRunner 確保所有 Bean (包括 LifecycleDemoBean) 初始化完成後執行。
     */
    @Bean
    public CommandLineRunner run(LifecycleDemoBean demoBean) {
        return args -> {
            System.out.println("\n--- [DEMO]Bean生命週期Phase 4: Ready for Use ---");
            demoBean.useBean();
            System.out.println("---------------------------------------------------\n");
            // 當應用程式關閉時，將觸發Phase5(@PreDestroy, destroy)
        };
    }
}
