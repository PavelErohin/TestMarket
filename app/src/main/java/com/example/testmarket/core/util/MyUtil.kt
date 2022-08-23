package com.example.testmarket.core.util

import java.text.NumberFormat
import java.util.*

class MyUtil {
  fun convertToPrice(int: Int): String {
    return "$" + NumberFormat.getNumberInstance(Locale.US).format(int) + ".00"
  }
  fun convertToPriceUS(int: Int): String {
    return "$" + NumberFormat.getNumberInstance(Locale.US).format(int) + " us"
  }
}