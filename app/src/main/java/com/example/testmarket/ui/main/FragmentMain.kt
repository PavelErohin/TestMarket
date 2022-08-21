package com.example.testmarket.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.testmarket.R
import com.example.testmarket.databinding.FragmentMainBinding
import com.example.testmarket.ui.base.viewBinding
import com.example.testmarket.viewmodel.MainScreenViewModel

class FragmentMain : Fragment(R.layout.fragment_main) {

  private val binding by viewBinding { FragmentMainBinding.bind(it) }
  private val viewModel by viewModels<MainScreenViewModel>()
  private val adapter =
    AdapterMainScreen(
      { categoryItem -> viewModel.onCategoryPressed(categoryItem.title) },
      { bestItem -> viewModel.onBestPressed(bestItem.id) },
      { toDetailsClick -> onHotClick(toDetailsClick) }
    )

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
/*    if (savedInstanceState == null) {
      setupBottomNavigationBar()
    }*/
    with(binding) {
      val layoutManager = GridLayoutManager(context, 2)
      layoutManager.spanSizeLookup = object : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
          return if (position < 5) 2 else 1
        }
      }
      recyclerView.layoutManager = layoutManager
      recyclerView.adapter = adapter
      viewModel.data.observe(viewLifecycleOwner, Observer {
        adapter.items = it
      })
      viewModel.filter.observe(viewLifecycleOwner, Observer {
        showFilterDialog()
      })
      //Toast.makeText(context /* todo ?requireContext()*/, "Нет сети!", Toast.LENGTH_LONG)
      //  .show()//todo observer
    }
  }

/*  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    setupBottomNavigationBar()
  }

  private fun setupBottomNavigationBar() {
    val navGraphIds = listOf(
      R.navigation.nav_graph,
      R.navigation.favorites__nav_graph,
      R.navigation.responses__nav_graph,
      R.navigation.profile__nav_graph
    )

    // Setup the bottom navigation view with a list of navigation graphs
    fragment_main_bottom_navigation.setupWithNavController(
      navGraphIds = navGraphIds,
      fragmentManager = childFragmentManager, // Самая важная строка
      containerId = R.id.fragment_main__nav_host_container,
      intent = requireActivity().intent
    )
  }*/

  private fun showFilterDialog() {
    DialogFilter().show(childFragmentManager, "TAG")
  }

  private fun onHotClick(id: Int) {
    findNavController().navigate(R.id.action_fragmentMain_to_fragmentDetails)
  }

}