package com.example.android.gintastic.detailedgintonic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.navArgs
import com.example.android.gintastic.R
import com.example.android.gintastic.database.AppDatabase
import com.example.android.gintastic.databinding.FragmentDetailedGinTonicBinding

class DetailedGinTonicFragment : Fragment() {
    private val args: DetailedGinTonicFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailedGinTonicBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detailed_gin_tonic, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).ginTonicDao

        val viewModelFactory = DetailedGinTonicViewModelFactory(dataSource,application, args.ginTonicId)
        val detailedGinTonicViewModel = ViewModelProvider(this, viewModelFactory).get(DetailedGinTonicViewModel::class.java)
        binding.detailedGinTonicViewModel = detailedGinTonicViewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

}