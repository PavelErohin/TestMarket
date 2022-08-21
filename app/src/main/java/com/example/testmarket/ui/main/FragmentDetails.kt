package com.example.testmarket.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.testmarket.R
import com.example.testmarket.databinding.FragmentDetailsBinding
import com.example.testmarket.ui.base.viewBinding
import com.example.testmarket.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentDetails : Fragment(R.layout.fragment_details) {

  private val binding by viewBinding { FragmentDetailsBinding.bind(it) }
  private val viewModel by viewModel<DetailsViewModel>()

  private val galleryAdapter = GalleryAdapter()
  private val colorAdapter =
    ColorAdapter { colorItem -> viewModel.onColorPressed(colorItem.itemId) }
  private val capacityAdapter =
    CapacityAdapter { capacityItem -> viewModel.onCapacityPressed(capacityItem.itemId) }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      rvGallery.adapter = galleryAdapter
      if (rvGallery.getOnFlingListener() == null)
        PagerSnapHelper().attachToRecyclerView(rvGallery)

      back.setOnClickListener { findNavController().popBackStack() }
      rvColor.adapter = colorAdapter

      rvCapacity.adapter = capacityAdapter

      viewModel.dataGallery.observe(viewLifecycleOwner) {
        galleryAdapter.items = it //todo сделать виджет галереи
      }
      viewModel.dataColor.observe(viewLifecycleOwner) {
        colorAdapter.items = it
      }
      viewModel.dataCapacity.observe(viewLifecycleOwner) {
        capacityAdapter.items = it
      }
      viewModel.dataDetails.observe(viewLifecycleOwner) {
        title.text = it.title
        cpu.text = it.cpu
        camera.text = it.camera
        ram.text = it.sd
        ssd.text = it.ssd
        price.text = it.price
        ratingBar.rating = it.rating
      }
    }
  }

  fun back() {
    findNavController().previousBackStackEntry
  }
}
