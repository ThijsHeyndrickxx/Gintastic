package com.example.android.gintastic.favouritegintonics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.gintastic.R
import com.example.android.gintastic.allgintonics.AllGinTonicsViewModelFactory
import com.example.android.gintastic.allgintonics.GinTonicAdapter
import com.example.android.gintastic.database.AppDatabase
import com.example.android.gintastic.databinding.FragmentFavouriteGinTonicsBinding

class FavouriteGinTonicsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFavouriteGinTonicsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favourite_gin_tonics, container, false
        )
        val application = requireNotNull(this.activity).application

        val dataSource = AppDatabase.getInstance(application).ginTonicDao

        val viewModelFactory = FavouriteGinTonicsViewModelFactory(dataSource,application)

        var favouriteGinTonicsViewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(FavouriteGinTonicsViewModel::class.java)
        binding.favouriteGinTonicsViewModel = favouriteGinTonicsViewModel
        binding.setLifecycleOwner(this)

        val adapter = GinTonicAdapter()
        binding.favouriteGinTonicsList.adapter = adapter
        favouriteGinTonicsViewModel.favouriteGinTonics.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root

    }

}