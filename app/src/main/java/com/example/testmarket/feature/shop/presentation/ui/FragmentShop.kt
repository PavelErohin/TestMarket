package com.example.testmarket.feature.shop.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testmarket.R
import com.example.testmarket.databinding.FragmentShopBinding
import com.example.testmarket.core.ui.viewBinding
import com.example.testmarket.feature.shop.presentation.ShopViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentShop : Fragment(R.layout.fragment_shop) {

  private val binding by viewBinding { FragmentShopBinding.bind(it) }
  private val viewModel by viewModel<ShopViewModel>()
  private val adapter =
    AdapterShopScreen(
      { titleSelectCategory -> viewModel.onCategoryPressed(titleSelectCategory) },
      { hotBuyId -> goBuyHot(hotBuyId) },
      { productDetailsId -> goToProductDetails(productDetailsId) },
      { likeBestPressedId -> viewModel.onLikeBestPressed(likeBestPressedId) },
      { _ -> showFilterDialog() }
    )

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    with(binding) {
      val layoutManager = GridLayoutManager(context, 2)
      layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
          return if (position < 5) 2 else 1
        }
      }
      recyclerView.layoutManager = layoutManager
      recyclerView.adapter = adapter
      viewModel.data.observe(viewLifecycleOwner) {
        adapter.items = it
      }
    }
  }

  private fun showFilterDialog() {
    findNavController().navigate(R.id.action_fragmentShop_to_dialogFilter)
  }

  private fun goToProductDetails(id: Int) {
    //id если буду делать дальше с фейковыми данными
    findNavController().navigate(R.id.action_fragmentShop_to_fragmentDetails)
  }

  private fun goBuyHot(id: Int) {
    findNavController().navigate(R.id.navigation_cart)
  }
}
