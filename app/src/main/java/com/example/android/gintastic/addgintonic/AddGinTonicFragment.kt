package com.example.android.gintastic.addgintonic

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.gintastic.MainActivity
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


        setTasteList(addGinTonicViewModel, binding)
        setAddButton(addGinTonicViewModel,binding)

        return binding.root

    }

    private fun setAddButton(addGinTonicViewModel: AddGinTonicViewModel, binding: FragmentAddGinTonicBinding) {

        binding.addGtButton.setOnClickListener{
            var name: String = binding.newGinTonicName.text.toString()
            var description: String = binding.newGinTonicDescription.text.toString()
            var taste: String = binding.selectedTaste.text.toString()

            if(!valid(name, taste, description)){

            } else {
                val gt = GinTonic()
                gt.name = name
                gt.taste = taste
                gt.description = description
                addGinTonicViewModel.addGinTonic(gt)
                Toast.makeText(requireActivity(), "$name was successfully added.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun valid(name: String, taste: String, description: String): Boolean {
        if(name.isEmpty() || name.isBlank()){
            Toast.makeText(requireActivity(), "Name cannot be blank!", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(taste.isEmpty() || taste.isBlank()){
            Toast.makeText(requireActivity(), "Taste cannot be blank!", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(description.isEmpty() || description.isBlank()){
                Toast.makeText(requireActivity(), "Description cannot be blank!", Toast.LENGTH_SHORT).show()
                return false
            }
        else {
            return true
        }
    }


    private fun setTasteList(viewModel: AddGinTonicViewModel, binding: FragmentAddGinTonicBinding) {
        var tasteList = viewModel.getTastes()
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, tasteList)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.selectedTaste.text = "No taste selected yet!"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val taste: String = tasteList[position]
                binding.selectedTaste.text = taste
            }



        }


    }


}