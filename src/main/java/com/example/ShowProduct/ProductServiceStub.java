package com.example.ShowProduct;

//使用我們自定義的註解標記這個類別
@MyComponent
public class ProductServiceStub {

    public void executeLogic() {
        System.out.println("[Stub] 成功呼叫ProductService的內部邏輯。");
    }
}
