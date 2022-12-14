package com.example.testmarket.feature.cart.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmarket.R
import com.example.testmarket.core.ui.viewBinding
import com.example.testmarket.databinding.FragmentCartBinding
import com.example.testmarket.feature.cart.presentation.CartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCart : Fragment(R.layout.fragment_cart) {

  private val binding by viewBinding { FragmentCartBinding.bind(it) }
  private val viewModel by viewModel<CartViewModel>()
  private val adapter =
    AdapterCartScreen(
      { back() },
      { plusClickId -> viewModel.plusClick(plusClickId) },
      { minusClickId -> viewModel.minusClick(minusClickId) },
      { deleteClickId -> viewModel.deleteClick(deleteClickId) },
      { imageClickId -> goToProductDetails(imageClickId) }
    )

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      val layoutManager = LinearLayoutManager(context)
      rvCart.layoutManager = layoutManager
      rvCart.adapter = adapter
      viewModel.dataCart.observe(viewLifecycleOwner) {
        adapter.items = it
      }
    }
  }

  private fun back() {
    findNavController().popBackStack()
  }

  private fun goToProductDetails(id: Int) {
    findNavController().navigate(R.id.action_fragmentCart_to_fragmentDetails)
  }
}