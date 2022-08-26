package com.example.testmarket.feature.details.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.testmarket.R
import com.example.testmarket.core.ui.viewBinding
import com.example.testmarket.databinding.FragmentDetailsBinding
import com.example.testmarket.databinding.ItemDetailsGalleryBinding
import com.example.testmarket.feature.details.presentation.DetailsViewModel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDetails : Fragment(R.layout.fragment_details) {

  private val binding by viewBinding { FragmentDetailsBinding.bind(it) }
  private val viewModel by viewModel<DetailsViewModel>()

  private val colorAdapter =
    ColorAdapter { colorItem -> viewModel.onColorPressed(colorItem.itemId) }
  private val capacityAdapter =
    CapacityAdapter { capacityItem -> viewModel.onCapacityPressed(capacityItem.itemId) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      carousel.registerLifecycle(lifecycle)

      carousel.carouselListener = object : CarouselListener {

        override fun onCreateViewHolder(
          layoutInflater: LayoutInflater, parent: ViewGroup
        ): ViewBinding {
          return ItemDetailsGalleryBinding.inflate(layoutInflater, parent, false)
        }

        override fun onBindViewHolder(
          binding: ViewBinding, item: CarouselItem, position: Int
        ) {
          val currentBinding = binding as ItemDetailsGalleryBinding
          currentBinding.imageView.apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImage(item, R.drawable.bg_dialog_spinner)
          }
        }
      }

      back.setOnClickListener { findNavController().popBackStack() }
      addToCartTop.setOnClickListener { addToCart() }
      addToCartBottom.setOnClickListener { addToCart() }
      addToFavorites.setOnClickListener { viewModel.addToFavorites() }

      rvColor.adapter = colorAdapter
      rvCapacity.adapter = capacityAdapter

      viewModel.dataColor.observe(viewLifecycleOwner) {
        colorAdapter.items = it
      }
      viewModel.dataCapacity.observe(viewLifecycleOwner) {
        capacityAdapter.items = it
      }
      viewModel.dataDetails.observe(viewLifecycleOwner) {
        carousel.setData(it.images)
        title.text = it.title
        cpu.text = it.cpu
        camera.text = it.camera
        ram.text = it.sd
        ssd.text = it.ssd
        price.text = it.price
        ratingBar.rating = it.rating
        addToFavorites.isSelected = it.isFavorites
      }
    }
  }

  private fun addToCart() {
    findNavController().navigate(R.id.navigation_cart)
  }

}
