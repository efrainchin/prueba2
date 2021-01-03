package com.efrain.pruebaalbo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efrain.pruebaalbo.R
import com.efrain.pruebaalbo.databinding.ItemBeersBinding
import com.efrain.pruebaalbo.model.Beers
import com.efrain.pruebaalbo.ui.HomeFragmentDirections
import com.efrain.pruebaalbo.utils.NetworkChecker
import kotlin.properties.Delegates


/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */

class BeersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var beers : List<Beers> by Delegates.observable(emptyList()){_, old, new ->
       DiffUtil.calculateDiff(object : DiffUtil.Callback(){
            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].id == new[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BeerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_beers, parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BeerViewHolder).bind(beers[position])

    override fun getItemCount(): Int = beers.size

    fun setItems(list: List<Beers>) {
        this.beers = list
        notifyDataSetChanged()
    }

    fun addLastPageItems(list: List<Beers>){
        val newList = beers + list
        beers = newList
        notifyDataSetChanged()
    }

    inner class BeerViewHolder(val binding: ItemBeersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(beers: Beers) {
            binding.beers = beers
            binding.clBeers.setOnClickListener {
                if (NetworkChecker.isNetworkConnected(binding.clBeers.context))
                    binding.clBeers.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(beers))
            }
            binding.executePendingBindings()
        }
    }

}

@BindingAdapter("loadImage")
fun loadImage(image: ImageView,url: String?){
    Glide.with(image)
        .load(url)
        .thumbnail(0.4f)
        .into(image)
}