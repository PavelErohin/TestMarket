package com.example.testmarket.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.testmarket.R
import com.example.testmarket.databinding.FragmentTabsBinding
import com.example.testmarket.ui.base.viewBinding
import com.example.testmarket.viewmodel.TabViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentTabs : Fragment(R.layout.fragment_tabs) {

  private val viewModel by viewModel<TabViewModel>()
  private val binding by viewBinding { FragmentTabsBinding.bind(it) }
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
    val navController = navHost.navController
    NavigationUI.setupWithNavController(binding.mainBottomNavigationView, navController)
    // private var badge = bottomNavigation.getOrCreateBadge(binding.mainBottomNavigationView)
    viewModel.sumProductCart.observe(viewLifecycleOwner) {
      val badge = binding.mainBottomNavigationView.getOrCreateBadge(R.id.nav_graph_cart)
      badge.isVisible = true
      val badgeValue = viewModel.sumProductCart.value
      if (badgeValue != null) {
        badge.number = badgeValue
      } else badge.number = 0

      if (badge.number == 0) {
        badge.isVisible = false
        badge.clearNumber()
      }
    }

/*
      Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG)
        .show()//todo observer network*/
  }
}