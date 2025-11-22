package com.example.ShowProduct.ioc;

//這是我們模擬Spring核心邏輯的類別
public class SimpleIocContainer {

    public static void runSimulation(Class<?> beanClass) throws Exception {

        System.out.println("--- 1. 檢查類別: " + beanClass.getName() + " ---");

        //【反射應用1:讀取註解(Annotation)】
        //isAnnotationPresent():檢查類別上是否有我們需要的MyComponent註解
        if (beanClass.isAnnotationPresent(MyComponent.class)) {

            System.out.println("✅發現@MyComponent標記！準備建立實例 (Bean)...");

            //【反射應用2:建立實例(Bean Creation)】
            //getDeclaredConstructor():透過反射取得該類別的建構子
            //newInstance():透過建構子動態地建立一個新的物件實例
            Object instance = beanClass.getDeclaredConstructor().newInstance();

            System.out.println("✨成功建立Bean實例: " + instance.getClass().getSimpleName());

            //【反射應用3:執行方法 (Method Invocation)】
            //雖然這個例子是直接轉換，但在Spring中，它甚至可以透過反射直接呼叫方法或設定欄位
            if (instance instanceof ProductServiceStub) {
                ((ProductServiceStub) instance).executeLogic();
            }

        } else {
            System.out.println("❌未發現@MyComponent標記，忽略此類別。");
        }
    }
}
