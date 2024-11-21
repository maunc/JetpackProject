package com.maunc.jetpackproject.ui.activity

import android.os.Bundle
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.maunc.jetpackmvvm.base.BaseActivity
import com.maunc.jetpackmvvm.utils.LogUtils
import com.maunc.jetpackproject.R
import com.maunc.jetpackproject.databinding.ActivityMainBinding
import com.maunc.jetpackproject.viewmodel.activity.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private val mainContainerId = R.id.nav_host_fragment_container
    private var currentDestinationId = R.id.fragment_home
    private val arrayFragmentId by lazy {
        IntArray(3).apply {
            this[0] = R.id.fragment_home
            this[1] = R.id.fragment_message
            this[2] = R.id.fragment_mine
        }
    }
    private val destinationChangeCallBack by lazy {
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            LogUtils.e("id->${destination.id},navigatorName->${destination.navigatorName},route->${destination.route}")
            currentDestinationId = destination.id
            when (currentDestinationId) {
                R.id.fragment_home -> {
                    mDatabind.homeButton.isSelected = true
                    mDatabind.messageButton.isSelected = false
                    mDatabind.mineButton.isSelected = false
                }

                R.id.fragment_message -> {
                    mDatabind.homeButton.isSelected = false
                    mDatabind.messageButton.isSelected = true
                    mDatabind.mineButton.isSelected = false
                }

                R.id.fragment_mine -> {
                    mDatabind.homeButton.isSelected = false
                    mDatabind.messageButton.isSelected = false
                    mDatabind.mineButton.isSelected = true
                }
            }
        }
    }

    private val buttonClickListener by lazy {
        OnClickListener { view ->
            arrayFragmentId.forEach { index ->
                val fragmentId = arrayFragmentId[index]
                fragmentId.takeIf {
                    it == currentDestinationId
                }?.let {

                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        navHostFragment =
            supportFragmentManager.findFragmentById(mainContainerId) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(destinationChangeCallBack)
        mDatabind.homeButton.setOnClickListener {
            if (currentDestinationId == R.id.fragment_mine) {
                navController.navigate(R.id.action_fragment_mine_to_fragment_home)
            }
            if (currentDestinationId == R.id.fragment_message) {
                navController.navigate(R.id.action_fragment_message_to_fragment_home)
            }
        }
        mDatabind.messageButton.setOnClickListener {
            if (currentDestinationId == R.id.fragment_home) {
                navController.navigate(R.id.action_fragment_home_to_fragment_message)
            }
            if (currentDestinationId == R.id.fragment_mine) {
                navController.navigate(R.id.action_fragment_mine_to_fragment_message)
            }
        }
        mDatabind.mineButton.setOnClickListener {
            if (currentDestinationId == R.id.fragment_home) {
                navController.navigate(R.id.action_fragment_home_to_fragment_mine)
            }
            if (currentDestinationId == R.id.fragment_message) {
                navController.navigate(R.id.action_fragment_message_to_fragment_mine)
            }
        }
    }

    override fun onFrontAndBackStateChanged(frontAndBackState: Boolean) {
    }

    override fun onNetworkStateChanged(netState: Boolean) {
    }

    override fun onScreenStateChanged(screenState: Boolean) {
    }
}