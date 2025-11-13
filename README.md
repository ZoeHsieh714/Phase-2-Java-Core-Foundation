## Java核心基礎練習: Stream API, Optional 與 DTO 轉換實戰  (Javaコア基礎練習: Stream API, Optional & DTO 変換実戦)

這是一個輕量級的 Spring Boot 練習專案，專注於鞏固 Java 8+ 的核心特性，特別是如何在分層架構中高效處理數據和預防空指標錯誤。
（このプロジェクトは、Java 8以降の主要な機能（Stream API, Optional）と、Spring Bootの層別化されたアーキテクチャへの適用を実践的に学ぶことを目的としています。）

### 🎯 學習目標與重點 (目標と学習ポイント)
本模組的核心實作位於 `ProductService.java` 中，展示了現代 Java 數據處理和錯誤預防的最佳實踐。
（本モジュールの中心的な実装は `ProductService.java` にあり、現代 Java のデータ処理とエラー予防のベストプラクティスを示しています。）

#### 模組 (モジュール) | 核心知識點 (コア知識点) | 實作檔案與方法 (実装ファイルとメソッド)
| **Stream API** | **數據高效處理:** `map`, `filter`, `sorted`, `collect`, `groupingBy` などの鏈式呼叫。（データの高効率処理: チェーン呼び出し） | `service/ProductService.java` (Ex 1, 2, 5, 6) |
| **Optional** | **徹底迴避 Null:** `orElseThrow`、`orElseGet`、`map` 等方法防止空指標異常。（Null回避の徹底: NullPointerException を防ぐ） | `service/ProductService.java` (Ex 4: `findAnyExpensiveProduct` / `findProductByIdOrThrow`) |
| **分層架構** | Entity（數據結構）到 DTO（傳輸格式）的乾淨數據轉換處理。（Entity から DTO へのクリーンなデータ変換処理。） | `controller/ProductRestController.java` |

### 🌐 網頁整合模組 (ウェブ統合モジュール)
本模組展示了 Spring MVC 如何將業務層的數據結果，透過模板引擎渲染至前端頁面。
（本モジュールは、Spring MVC がビジネス層のデータ結果をテンプレートエンジンを通じてフロントエンドページにレンダリングする方法を示しています。）

#### 項目 (項目) | 詳細說明 (詳細説明) | 相關檔案 (関連ファイル)
| **View Controller** | 使用 `@Controller` 處理請求，並回傳 Thymeleaf 視圖名稱。（`@Controller` を使用してリクエストを処理し、Thymeleaf のビュー名を返却する。） | `controller/ProductWebController.java` |
| **數據傳遞 (データ転送)** | 透過 `Model` 將後端數據傳輸至 Thymeleaf 模板。（`Model` を介してバックエンドデータを Thymeleaf テンプレートへ転送する。） | `controller/ProductWebController.java` |
| **Thymeleaf 處理 (Thymeleaf 処理)** | 使用 `th:each` 和 `th:text` 有效率地描繪 Stream API 的處理結果。（`th:each` や `th:text` を使用して Stream API の処理結果を効率的に描画する。） | `src/main/resources/templates/products.html` |

### 🛠️ 技術棧與運行方式 (技術スタックと実行方法)
#### 使用技術 (使用技術)
* **Java Version:** Java 21
* **Framework:** Spring Boot 3.5.7
* **Template Engine:** Thymeleaf
* **Build Tool:** Gradle

#### 運行方法 (実行方法)
1.  **Clone 專案 (プロジェクトのクローン):**
    ```bash
    git clone [https://github.com/ZoeHsieh714/java-springboot-core-phase1-partA]
    ```
2.  **啟動應用程式 (アプリケーションの起動):**
    ```bash
    ./gradlew bootRun
    ```

#### 驗證範例 (検証サンプル)
請在應用程式啟動後 (通常在 `http://localhost:8080`) 訪問以下路徑進行驗證。

| 驗證項目 (検証項目) | 訪問路徑 (アクセス先) | 預期結果 (期待される結果) |
| **Web 頁面 (Thymeleaf)** | `http://localhost:8080/web/products` | 頁面顯示 Stream API 處理後的商品列表。（ページ上に Stream API の処理結果（商品リスト）が表示されます。） |
| **JSON API** | `http://localhost:8080/api/rest/products-all-dto` | 回傳所有商品的 DTO 列表，為 JSON 格式。（全商品の DTO リストが JSON 形式で返却されます。） |
| **Optional 例外處理** | `http://localhost:8080/api/rest/product/1` | 回傳 ID=1 的商品詳細。不存在的 ID 將回傳 `404 Not Found`。（ID=1の商品詳細が返却されます。存在しないIDの場合、`404 Not Found` が返します。）

---
