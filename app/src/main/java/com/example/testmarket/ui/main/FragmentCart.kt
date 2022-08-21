package com.example.testmarket.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmarket.R
import com.example.testmarket.databinding.FragmentCartBinding
import com.example.testmarket.ui.base.viewBinding
import com.example.testmarket.viewmodel.CartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCart : Fragment(R.layout.fragment_cart) {

  private val binding by viewBinding { FragmentCartBinding.bind(it) }
  private val viewModel by viewModel<CartViewModel>()
  private val adapter =
    AdapterCartScreen(
      { plusClick -> viewModel.plusClick(plusClick.id) },
      { minusClick -> viewModel.minusClick(minusClick.id) },
      { deleteClick -> viewModel.deleteClick(deleteClick.id) }/*,
      { checkoutClick -> viewModel.checkoutClick(checkoutClick) }*/
    )

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      val layoutManager = LinearLayoutManager(context)
      rvCart.layoutManager = layoutManager
      rvCart.adapter = adapter
      viewModel.dataCart.observe(viewLifecycleOwner, Observer {
        adapter.items = it
      })
    }
  }
}