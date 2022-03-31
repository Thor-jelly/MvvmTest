package com.jelly.thor.mvvmtest;

import com.jelly.thor.base.base.BaseVDBActivity;
import com.jelly.thor.mvvmtest.databinding.ActivityMainBinding;

public class MainActivity extends BaseVDBActivity<MainVm, ActivityMainBinding> {


    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getVariableId() {
        return BR.mainVm;
    }
}