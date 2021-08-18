package com.example.android.gintastic.allgintonics

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.gintastic.R
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.databinding.ListItemGinTonicBinding
import com.example.android.gintastic.favouritegintonics.FavouriteGinTonicsFragment
import com.example.android.gintastic.favouritegintonics.FavouriteGinTonicsFragmentDirections


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

        var fragment: Fragment = view.findFragment()
        var string = fragment.toString().substring(0,10).toRegex()
        string.matches("AllGinToni")
        if(string.matches("AllGinToni")){
            var direction = AllGinTonicsFragmentDirections.actionAllGinTonicsFragmentToDetailedGinTonicFragment(ginTonic.ginTonicId)
            view.findNavController().navigate(direction)
        }else if(string.matches("FavouriteG")){
            var direction = FavouriteGinTonicsFragmentDirections.actionFavouriteGinTonicsFragmentToDetailedGinTonicFragment(ginTonic.ginTonicId)
            view.findNavController().navigate(direction)
        }





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