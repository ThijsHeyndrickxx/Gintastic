package com.example.android.gintastic.addgintonic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.android.gintastic.R
import com.example.android.gintastic.database.AppDatabase
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.databinding.FragmentAddGinTonicBinding

class AddGinTonicFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddGinTonicBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_gin_tonic, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource =  AppDatabase.getInstance(application).ginTonicDao

        val viewModelFactory = AddGinTonicViewModelFactory(dataSource,application)

        val addGinTonicViewModel = ViewModelProvider(this, viewModelFactory).get(AddGinTonicViewModel::class.java)

        binding.addGinTonicViewModel = addGinTonicViewModel

        binding.setLifecycleOwner(this)

        binding.addGtButton.setOnClickListener{
            val gt = GinTonic()
            gt.name = binding.newGinTonicName.text.toString()
            addGinTonicViewModel.addGinTonic(gt)
            Toast.makeText(requireActivity(), gt.name + "was successfully added.", Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }


}