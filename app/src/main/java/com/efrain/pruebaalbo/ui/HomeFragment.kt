package com.efrain.pruebaalbo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.efrain.pruebaalbo.R
import com.efrain.pruebaalbo.adapters.BeersAdapter
import com.efrain.pruebaalbo.databinding.FragmentHomeBinding
import com.efrain.pruebaalbo.ui.base.BaseFragment
import com.efrain.pruebaalbo.utils.Constants.Companion.PAGE
import com.efrain.pruebaalbo.utils.Constants.Companion.PER_PAGE
import com.efrain.pruebaalbo.utils.NetworkChecker
import com.efrain.pruebaalbo.utils.PaginationScrollListener
import com.efrain.pruebaalbo.viewmodels.BeersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val beersViewModel: BeersViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapter = BeersAdapter()
    private var CURENT_PAGE = PAGE
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var onScrollListener: PaginationScrollListener? = null

    override fun onViewCreated() {
        mainViewModel.setTile("Beers")
        binding.swipe.isRefreshing = true
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerBeers.layoutManager = linearLayoutManager
        binding.recyclerBeers.adapter = adapter
        beersViewModel.getBeers(CURENT_PAGE, PER_PAGE)
        observeBeers()
        binding.swipe.setOnRefreshListener {
            CURENT_PAGE = PAGE
            beersViewModel.getBeers(CURENT_PAGE, PER_PAGE)
        }
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
            FragmentHomeBinding.inflate(inflater, container, false)

    private fun observeBeers() {
        beersViewModel.getObserveLiveDataBeers().observe(viewLifecycleOwner, Observer {
            binding.swipe.isRefreshing = false
            onScrollRecyclerView()
            if (!it.isNullOrEmpty()) {
                if (CURENT_PAGE == PAGE)
                    adapter.setItems(it)
                else {
                    isLoading = false
                    adapter.addLastPageItems(it)
                }
            }
        })
    }

    private fun onScrollRecyclerView() {
        if (NetworkChecker.isNetworkConnected(requireContext())) {
            if (onScrollListener == null) {
                onScrollListener = object : PaginationScrollListener(linearLayoutManager) {
                    override fun isLastPage(): Boolean = isLastPage
                    override fun isLoading(): Boolean = isLoading

                    override fun loadMoreItems() {
                        binding.swipe.isRefreshing = true
                        isLoading = true
                        CURENT_PAGE++
                        beersViewModel.getBeers(CURENT_PAGE, PER_PAGE)
                    }
                }
                binding.recyclerBeers.addOnScrollListener(onScrollListener!!)
            }
        } else {
            onScrollListener?.let {
                binding.recyclerBeers.removeOnScrollListener(it)
            }
        }
    }
}