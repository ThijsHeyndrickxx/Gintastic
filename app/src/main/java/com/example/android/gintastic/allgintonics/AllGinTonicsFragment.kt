package com.example.android.gintastic.allgintonics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.gintastic.R
import com.example.android.gintastic.database.AppDatabase
import com.example.android.gintastic.databinding.FragmentAllGinTonicsBinding



class AllGinTonicsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAllGinTonicsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_all_gin_tonics, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = AppDatabase.getInstance(application).ginTonicDao

        val viewModelFactory =  AllGinTonicsViewModelFactory(dataSource, application)

         var allGinTonicsViewModel = ViewModelProvider(
            this, viewModelFactory).get(AllGinTonicsViewModel::class.java)

        binding.allGinTonicsViewModel = allGinTonicsViewModel

        binding.setLifecycleOwner(this)

        val adapter = GinTonicAdapter()
        binding.ginTonicList.adapter = adapter

        allGinTonicsViewModel.ginTonics.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }


}