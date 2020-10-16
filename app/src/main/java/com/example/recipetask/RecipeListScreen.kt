package com.example.recipetask

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipetask.Api.RecipeApiFilter
import com.example.recipetask.Model.RecipeAdapter
import com.example.recipetask.databinding.RecipeListScreenFragmentBinding

class RecipeListScreen : Fragment() {
    private val viewModel: RecipeListScreenViewModel by lazy {
        ViewModelProvider(this).get(RecipeListScreenViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPref: SharedPreferences? =
            this.activity?.getSharedPreferences("MyShared", Context.MODE_PRIVATE)
        viewModel.filter = sharedPref?.getString("filter", null)

        val binding = RecipeListScreenFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recycleview.adapter = RecipeAdapter(RecipeAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(RecipeListScreenDirections.actionShowDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPref: SharedPreferences? =
            this.activity?.getSharedPreferences("MyShared", Context.MODE_PRIVATE)
        var editor = sharedPref?.edit()
        var filter: String = ""
        when (item.itemId) {
            R.id.sort_calories -> {
                filter = RecipeApiFilter.SortByCalories.value
                editor?.putString("filter", RecipeApiFilter.SortByCalories.value)?.commit()
            }
            R.id.sort_fats -> {
                filter = RecipeApiFilter.SortByFats.value
                editor?.putString("filter", RecipeApiFilter.SortByFats.value)?.commit()
            }

        }
        viewModel.updateFilter(
            filter
        )
        return true
    }
}