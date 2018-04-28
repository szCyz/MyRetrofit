package com.example.hsxfd.cyzretrofit.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void showError(Context context) {
        Toast.makeText(context, "请检查网络.", Toast.LENGTH_SHORT).show();
    }
}