package com.example.android.gintastic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.android.gintastic.R
import com.example.android.gintastic.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        setBindings(binding)

        return binding.root
    }
    private fun setBindings(binding: FragmentHomeBinding){
        binding.mainButtonAll.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_allGinTonicsFragment)
        }
        binding.mainButtonNew.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addGinTonicFragment)
        }
        binding.mainButtonFav.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_favouriteGinTonicsFragment)
        }
    }

}