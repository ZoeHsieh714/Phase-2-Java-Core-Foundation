# Phase-1-Java-Core-Foundation: Java核心基礎與Spring底層原理實戰
## (Phase-1-Java-Core-Foundation: Javaコア基礎とSpring基盤原則の実践)

這個專案標誌著 Spring Boot 學習的第一階段完成，它是一個完全整合的實戰範例，涵蓋了 Java 8+ 的核心特性、分層架構，以及 Spring IoC 容器運作的底層原理（反射與生命週期）。

（このプロジェクトは、Spring Boot 学習の第一段階完了を示すものです。Java 8+ のコア機能、層別化アーキテクチャ、および Spring IoC コンテナの基盤原理（反射とライフサイクル）を網羅した統合的な実践例です。）

---

## 🎯 階段一：學習目標與模組總覽 (目標とモジュール概要)

本專案分為三個主要模組，每個模組都對應一個 Spring Boot 應用程式的關鍵環節。

### 1. 數據處理與 API 層 (データ処理と API 層)

| 模組 (モジュール) | 核心知識點 (コア知識点) | 實作檔案與方法 (実装ファイルとメソッド) |
| :--- | :--- | :--- |
| **Stream API** | **數據高效處理:** `map`, `filter`, `collect` 等鏈式呼叫，用於資料轉換與聚合。（データ高効率処理: チェイン呼び出し） | `service/ProductService.java` (Ex 1, 2, 5, 6) |
| **Optional** | **徹底迴避 Null:** `orElseThrow`、`orElseGet`、`map` 等方法，用於單一資源查找和錯誤預防。（Null回避の徹底: エラー予防） | `service/ProductService.java` (Ex 4: `findProductByIdOrThrow`) |
| **分層架構** | Entity 到 DTO 的乾淨數據轉換處理。（Entity から DTO へのクリーンなデータ変換処理。） | `controller/ProductRestController.java` |

### 2. 核心原理與生命週期 (コア原則とライフサイクル)

本模組展示了 Spring IoC 容器工作的基石：反射、註解以及 Bean 的生命週期管理。
（本モジュールは、Spring IoC コンテナの基盤となる動作、すなわち反射、アノテーション、および Bean のライフサイクル管理を示しています。）

| 模組 (モジュール) | 核心知識點 (コア知識点) | 實作檔案與方法 (実装ファイルとメソッド) |
| :--- | :--- | :--- |
| **反射與註解** | **IoC 容器原理模擬:** 透過反射 (`newInstance()`) 讀取註解，實現元件掃描與實例化。(反射 (newInstance()) を通じてアノテーションを読み取り、コンポーネントのスキャンとインスタンス化を実現します。) | `ioc/SimpleIocContainer.java` (`runSimulation`) |
| **Bean 生命週期** | **初始化與銷毀順序:** 證明 `Constructor` -> `@PostConstruct` -> `@PreDestroy` 的嚴格執行順序。(Constructor -> @PostConstruct -> @PreDestroy の厳格な実行順序を証明します。) | `service/LifecycleDemoBean.java` |

### 3. 網頁整合 (ウェブ統合)

| 項目 (項目) | 詳細說明 (詳細説明) | 相關檔案 (関連ファイル) |
| :--- | :--- | :--- |
| **View Controller** | 使用 `@Controller` 處理請求，並回傳 Thymeleaf 視圖名稱。（`@Controller` を使用し、Thymeleaf のビュー名を返却する。） | `controller/ProductWebController.java` |
| **Thymeleaf 處理** | 使用 `th:each` 和 `th:text` 有效率地描繪 Stream API 的處理結果。（`th:each` や `th:text` で Stream API の結果を効率的に描画する。） | `src/main/resources/templates/products.html` |

---

## 🛠️ 技術棧與運行方式 (技術スタックと実行方法)

### 使用技術 (使用技術)
* **Java Version:** Java 21
* **Framework:** Spring Boot 3.5.7
* **Web/Template:** Spring MVC, Thymeleaf
* **Build Tool:** Gradle

### 運行與驗證 (実行と検証)

1.  **Clone 專案 (プロジェクトのクローン):**
    ```bash
    git clone [https://github.com/ZoeHsieh714/Phase-1-Java-Core-Foundation]
    ```
2.  **啟動應用程式 (アプリケーションの起動):**
    ```bash
    ./gradlew bootRun
    ```

請在應用程式啟動後，觀察 Console 輸出（用於驗證**核心原理**）並訪問以下路徑：

| 驗證項目 (検証項目) | 訪問路徑 (アクセス先) | 預期結果 (期待される結果) |
| :--- | :--- | :--- |
| **Web 頁面 (Thymeleaf)** | `http://localhost:8080/web/products` | 頁面顯示 Stream API 處理後的商品列表。（ページ上に Stream API の処理結果が表示されます。） |
| **Console 驗證** | **啟動時** | 觀察 `LifecycleDemoBean` 和 `SimpleIocContainer` 的輸出順序。( LifecycleDemoBean と SimpleIocContainer の出力順序を検証します。)|
| **Optional 例外處理** | `http://localhost:8080/api/rest/product/999` | 應回傳 `404 Not Found`，證明 Optional 錯誤處理機制正確運作。( 404 Not Found が返却されることで、Optional のエラー処理メカニズムが正しく機能していることを証明します。) |

---
