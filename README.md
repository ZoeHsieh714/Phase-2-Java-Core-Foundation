# Phase 2: Spring Data JPA 實戰與數據持久化 

## (Phase 2: Spring Data JPAの実践とデータ統合)

本階段將專案從使用靜態 Mock 數據升級為整合 **Spring Data JPA** 和 **H2 內嵌資料庫**。我們實現了完整的持久化層，使應用程式能夠在啟動時載入數據、並透過 Repository 介面操作資料庫。

(本フェーズでは、静的なモックデータを使用する状態から、**Spring Data JPA** と **H2 インメモリデータベース**を統合した状態にプロジェクトをアップグレードしました。持続性レイヤーを完全に実装し、アプリケーション起動時のデータロード、および Repository インターフェース経由のデータベース操作を可能にしました。)

---

## ✨ 階段二主要目標與實現 (Phase2の主要目標とモジュール概要)

| 目標 | 說明 | 關鍵變動(主な変更点) |
| :--- | :--- | :--- |
| **持久化配置(持続性の構成)** | 設定 H2 數據源並啟用 JPA/Hibernate。(H2 データソースを設定し、JPA/Hibernate を有効化。) | `application.properties`, `build.gradle` |
| **實體轉換(エンティティへの変換)** | 將 `Product` 類別轉換為 JPA `Entity`。(`Product` クラスを JPA `Entity` に変換。) | `Product.java` 添加 `@Entity`, `@Id`, `@GeneratedValue`(`Product.java` に `@Entity`, `@Id`, `@GeneratedValue` を追加 ) |
| **數據存取層(データアクセス層)** | 建立 `ProductRepository` 介面，繼承 `JpaRepository`，無需實作 CRUD 邏輯。(`JpaRepository` を継承する `ProductRepository` インターフェースを作成し、CRUD 実装を不要に。) | 新增 `ProductRepository.java` (`ProductRepository.java` を新規作成 )|
| **業務邏輯更新(ビジネスロジックの更新)** | `ProductService` 注入 Repository，所有數據操作改為調用 JPA 方法。( `ProductService` に Repository をインジェクションし、すべてのデータ操作を JPA メソッドに移行。) | `ProductService.java` |
| **啟動載入(初期データロード)** | 應用程式啟動時自動載入測試數據到 H2 資料庫。(アプリケーション起動時にテストデータを H2 データベースへ自動でロード。) | `ProductRestController.java` 建構子中呼叫 `loadInitialData()`　(`ProductRestController.java` のコンストラクタで `loadInitialData()` を呼び出し) |

---

## 🛠️ 技術棧 (技術スタック)

* **後端框架(バックエンドフレームワーク：)：** Spring Boot 3.3.0
* **持久化(永続化)：** Spring Data JPA
* **資料庫(データベース)：** H2 Database (In-Memory Mode)
* **構建工具(ビルドツール)：** Gradle 8.x
* **環境要求(環境要件)：** Java 21 (LTS)

## 🚀 啟動與驗證 (実行と検証)

1.  確保您的 `JAVA_HOME` 環境變數設定為 JDK 21。
   ( `JAVA_HOME` 環境変数が JDK 21 に設定されていることを確認してください。)
2.  在專案根目錄下打開終端機。
   (プロジェクトのルートディレクトリでターミナルを開きます。)
3.  執行啟動指令：
   (以下の起動コマンドを実行します。)
    ```bash
    ./gradlew bootRun
    ```
4.  應用程式將在 `http://localhost:8080` 啟動。
   (アプリケーションは `http://localhost:8080` で起動します。)

---

## 🔗 驗證 API 端點 (APIエンドポイントの検証)

以下 API 將從 H2 資料庫中讀取數據：
(以下の API は H2 データベースからデータを読み取ります。)

| 類型(タイプ) | 端點 (エンドポイント) | 說明 |
| :--- | :--- | :--- |
| **GET** | `http://localhost:8080/api/rest/products-all-dto` | 獲取所有產品 (DTO 格式) (全製品リストを取得 (DTO 形式))|
| **GET** | `http://localhost:8080/api/rest/products/books/instock` | 獲取在庫的書籍列表 (在庫のある書籍リストを取得)|
| **GET** | `http://localhost:8080/api/rest/product/{id}` | 根據 ID 獲取產品實體 (JPA 驅動) (ID に基づいて製品エンティティを取得 (JPA駆動)) |
| **WEB** | `http://localhost:8080/web/products` | 顯示 Thymeleaf 網頁產品列表 (Thymeleaf による製品リストページを表示) |
| **DB Console**| `http://localhost:8080/h2-console` | H2 資料庫管理介 (H2 データベース管理コンソール) |

---
