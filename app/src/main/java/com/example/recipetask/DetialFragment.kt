package com.example.recipetask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipetask.databinding.FragmentDetialBinding



class DetialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application
        val binding = FragmentDetialBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val recipeItem = DetialFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(recipeItem, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }

}