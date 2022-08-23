package com.example.testmarket.ui.main

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.example.testmarket.R
import com.example.testmarket.databinding.DialogFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogFilter() : BottomSheetDialogFragment() {
//todo !фильтр - обработка (парсер строк)

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val binding = DialogFilterBinding.inflate(LayoutInflater.from(requireContext()))
    val dialog = BottomSheetDialog(requireContext(), R.style.FilterDialog)
    dialog.setContentView(binding.root)

    with(binding) {
      close.setOnClickListener {
        findNavController().popBackStack()
      }
      done.setOnClickListener {
        findNavController().popBackStack()
      }
    }
    return dialog
  }
}