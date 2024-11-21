package com.maunc.jetpackproject.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.maunc.jetpackmvvm.base.BaseActivity
import com.maunc.jetpackproject.R
import com.maunc.jetpackproject.databinding.ActivityMainBinding
import com.maunc.jetpackproject.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onFrontAndBackStateChanged(frontAndBackState: Boolean) {
    }

    override fun onNetworkStateChanged(netState: Boolean) {
    }

    override fun onScreenStateChanged(screenState: Boolean) {
    }

}