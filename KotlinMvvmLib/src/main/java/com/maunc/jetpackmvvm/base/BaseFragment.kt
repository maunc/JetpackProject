package com.maunc.jetpackmvvm.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.maunc.jetpackmvvm.ext.getVmClazz
import com.maunc.jetpackmvvm.ext.inflateBindingWithGeneric
import com.maunc.jetpackmvvm.receiver.NetWorkStateManager
import com.maunc.jetpackmvvm.utils.LogUtils

abstract class BaseFragment<VM : BaseViewModel<*>, DB : ViewDataBinding> : Fragment() {

    private val TAG: String = this.javaClass.simpleName

    lateinit var mViewModel: VM

    //该类绑定的ViewDataBinding
    private var _binding: DB? = null
    val mDatabind: DB get() = _binding!!

    lateinit var mActivity: AppCompatActivity

    private var isFirst: Boolean = true //是否第一次加载

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    abstract fun initData()

    /**
     * 加载后返回Fragment回调
     */
    abstract fun onRestart()

    /**
     * 创建观察者
     */
    abstract fun createObserver()

    /**
     * 网络变化监听
     */
    abstract fun onNetworkStateChanged(netState: Boolean)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logLifecycle("onAttach")
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        logLifecycle("onCreateView")
        _binding = inflateBindingWithGeneric(inflater, container, false)
        return mDatabind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logLifecycle("onViewCreated")
        isFirst = true
        mViewModel = createViewModel()
        initView(savedInstanceState)
        createObserver()
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
        if (isFirst) {
            initData()
        }
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
        if (!isFirst) {
            onRestart()
        }
        isFirst = false
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logLifecycle("onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
        isFirst = true
    }

    override fun onDetach() {
        super.onDetach()
        logLifecycle("onDetach")
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this)[getVmClazz(this)]
    }

    /**
     * 获取ViewModel
     */
    fun <T : BaseViewModel<*>> getViewModel(quickViewModel: Class<T>): T {
        return ViewModelProvider(this)[quickViewModel]
    }

    private fun logLifecycle(lifecycle: String) {
        LogUtils.i(TAG, lifecycle)
    }
}