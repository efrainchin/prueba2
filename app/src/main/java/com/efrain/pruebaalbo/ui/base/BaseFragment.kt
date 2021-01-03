package com.efrain.pruebaalbo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.efrain.pruebaalbo.viewmodels.MainViewModel


/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */

abstract class BaseFragment<VB: ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeMainViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected abstract fun onViewCreated()

    /**
     * It returns [VB] which is assigned to [mViewBinding] and used in [onCreate]
     */
    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    protected fun onBackPressed() = activity?.onBackPressed()

    fun initializeMainViewModel() {
        activity?.run {
            val mainViewModel1: MainViewModel by viewModels()
            mainViewModel = mainViewModel1
        } ?: throw Exception("Invalid Activity")
    }

}