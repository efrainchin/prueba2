package com.efrain.pruebaalbo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.efrain.pruebaalbo.R
import com.efrain.pruebaalbo.adapters.FoodPairingAdapter
import com.efrain.pruebaalbo.databinding.FragmentDetailBinding
import com.efrain.pruebaalbo.model.Beers
import com.efrain.pruebaalbo.model.DetailBeers
import com.efrain.pruebaalbo.ui.base.BaseFragment
import com.efrain.pruebaalbo.viewmodels.BeersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val beersViewModel: BeersViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private val adaper = FoodPairingAdapter()

    override fun onViewCreated() {
        binding.swipe.isRefreshing = true
        mainViewModel.setTile(args.beer.name)
        binding.recyclerFoodPairing.adapter = adaper
        observeBeers()
        beersViewModel.getBeerById(args.beer.id)
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentDetailBinding.inflate(inflater, container, false)

    private fun observeBeers(){
        beersViewModel.getObserveLiveDetailBeer().observe(viewLifecycleOwner, Observer {
            binding.swipe.isRefreshing = false
            if (!it.isNullOrEmpty()) {
                val detailBeers: DetailBeers = it.first()
                binding.detailBeer = detailBeers
                adaper.setItems(detailBeers.foodPairing)
                binding.executePendingBindings()
            }
        })
    }
}