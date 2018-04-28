package com.example.hsxfd.cyzretrofit.presenter;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * 基本view，MVP模式中的view需实现该view
 */
public interface View {
    //为了防止内存泄漏的风险，使用了第三方库rxlifecycle
    LifecycleTransformer bindLifecycle();
}
