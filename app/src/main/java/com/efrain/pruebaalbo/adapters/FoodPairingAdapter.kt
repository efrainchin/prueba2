package com.efrain.pruebaalbo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efrain.pruebaalbo.R
import com.efrain.pruebaalbo.databinding.ItemBeersBinding
import com.efrain.pruebaalbo.databinding.ItemFoodPairingBinding
import com.efrain.pruebaalbo.model.Beers
import com.efrain.pruebaalbo.ui.HomeFragmentDirections


/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */

class FoodPairingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        FoodPairingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_food_pairing, parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as FoodPairingViewHolder).bind(items[position])

    override fun getItemCount(): Int = items.size

    fun setItems(list: List<String>) {
        this.items = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class FoodPairingViewHolder(val binding: ItemFoodPairingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.title = title
            binding.executePendingBindings()
        }
    }
}