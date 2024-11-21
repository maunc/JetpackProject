package com.maunc.jetpackproject.ui.fragment

import android.os.Bundle
import com.maunc.jetpackmvvm.base.BaseFragment
import com.maunc.jetpackproject.databinding.FragmentHomeBinding
import com.maunc.jetpackproject.viewmodel.fragment.HomeViewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
    }

    override fun onRestart() {
    }

    override fun createObserver() {
    }

    override fun onNetworkStateChanged(netState: Boolean) {
    }
}