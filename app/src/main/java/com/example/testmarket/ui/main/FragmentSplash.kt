package com.example.testmarket.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testmarket.R

class FragmentSplash : Fragment(R.layout.fragment_splash) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.postDelayed(
      {
        findNavController().navigate(R.id.action_fragmentSplash_to_fragmentMain)
      },
      800
    )
  }
}