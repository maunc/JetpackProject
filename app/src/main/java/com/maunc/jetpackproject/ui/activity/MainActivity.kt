package com.maunc.jetpackproject.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.maunc.jetpackmvvm.base.BaseActivity
import com.maunc.jetpackproject.databinding.ActivityMainBinding
import com.maunc.jetpackproject.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
    }

    override fun createObserver() {
        super.createObserver()
    }

    override fun onFrontAndBackStateChanged(frontAndBackState: Boolean) {
    }

    override fun onNetworkStateChanged(netState: Boolean) {
    }

    override fun onScreenStateChanged(screenState: Boolean) {
    }

}