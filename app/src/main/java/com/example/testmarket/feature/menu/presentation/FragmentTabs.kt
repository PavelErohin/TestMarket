package com.example.testmarket.feature.menu.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.testmarket.R
import com.example.testmarket.core.ui.viewBinding
import com.example.testmarket.databinding.FragmentTabsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentTabs : Fragment(R.layout.fragment_tabs) {

  private val viewModel by viewModel<TabViewModel>()
  private val binding by viewBinding { FragmentTabsBinding.bind(it) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
    val navController = navHost.navController

    NavigationUI.setupWithNavController(binding.mainBottomNavigationView, navController)

    viewModel.sumProductCart.observe(viewLifecycleOwner) {
      val badge = binding.mainBottomNavigationView.getOrCreateBadge(R.id.navigation_cart)
      badge.isVisible = true
      badge.number = it ?: 0
      if (badge.number == 0) {
        badge.isVisible = false
        badge.clearNumber()
      }
    }
  }
}