package com.scc.lib.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding,VM extends ViewModel> extends AppCompatActivity {
    public VB binding;
    public VM vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type superclass = getClass().getGenericSuperclass();
        Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        Class<? extends ViewModel > vmClass = (Class<? extends ViewModel>) ((ParameterizedType) superclass).getActualTypeArguments()[1];
        try {
            if (!ViewModel.class.getName().equals(vmClass.getClass().getName())) {
                vm = (VM) new ViewModelProvider(this).get(vmClass);
            }
            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class);
            binding = (VB) method.invoke(null, getLayoutInflater());
            setContentView(binding.getRoot());
        } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
            e.printStackTrace();
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
