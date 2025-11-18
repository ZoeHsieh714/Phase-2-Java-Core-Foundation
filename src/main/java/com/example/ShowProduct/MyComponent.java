package com.example.ShowProduct;

import java.lang.annotation.*;

//這是最關鍵的設定：告訴Java保留這個註解，讓程式在運行時可以讀取它
@Retention(RetentionPolicy.RUNTIME)
// 這個註解只能用在 TYPE (類別、介面、Enum) 上
@Target(ElementType.TYPE)
public @interface MyComponent {}
