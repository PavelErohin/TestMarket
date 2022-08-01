package com.example.testmarket.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.testmarket.R
import com.example.testmarket.databinding.FragmentMainBinding
import com.example.testmarket.ui.base.viewBinding
import com.example.testmarket.viewmodel.MainScreenViewModel


class FragmentMain : Fragment(R.layout.fragment_main) {

  private val binding by viewBinding { FragmentMainBinding.bind(it) }
  private val viewModel by viewModels<MainScreenViewModel>()
  private val adapter = MainScreenAdapter()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      val layoutManager: GridLayoutManager
      layoutManager = GridLayoutManager(context, 2)
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
 /*     viewModel.filter.observe(viewLifecycleOwner, Observer {

      })*/

      /*     viewModel.filter.observe(viewLifecycleOwner, Observer {
             adapter.items = it
           })*/
      //Toast.makeText(context /* todo ?requireContext()*/, "Нет сети!", Toast.LENGTH_LONG)
      //  .show()//todo observer
    }

  }
}