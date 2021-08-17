package com.example.android.gintastic.allgintonics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.gintastic.HomeFragmentDirections
import com.example.android.gintastic.R
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.databinding.ListItemGinTonicBinding

class GinTonicAdapter: ListAdapter<GinTonic, RecyclerView.ViewHolder>(GinTonicDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GinTonicViewHolder(ListItemGinTonicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ginTonic = getItem(position)
        (holder as GinTonicViewHolder).bind(ginTonic)
    }
}

class GinTonicViewHolder(private val binding: ListItemGinTonicBinding)
    : RecyclerView.ViewHolder(binding.root)
{
    init {
        binding.setClickListener {
            binding.ginTonic?.let {
                ginTonic ->
                navigateToDetailedGinTonic(ginTonic,it)
            }
        }
    }

    private fun navigateToDetailedGinTonic(ginTonic: GinTonic, view: View) {
        val direction =
            AllGinTonicsFragmentDirections.actionAllGinTonicsFragmentToDetailedGinTonicFragment(ginTonic.ginTonicId)
        view.findNavController().navigate(direction)
    }

    fun bind(item: GinTonic) {
            binding.apply {
                ginTonic = item
                executePendingBindings()
            }
        }
}

private class GinTonicDiffCallback: DiffUtil.ItemCallback<GinTonic>() {
    override fun areItemsTheSame(oldItem: GinTonic, newItem: GinTonic): Boolean {
        return oldItem.ginTonicId == newItem.ginTonicId
    }

    override fun areContentsTheSame(oldItem: GinTonic, newItem: GinTonic): Boolean {
        return oldItem == newItem
    }
}