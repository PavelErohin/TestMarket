package com.example.testmarket.featureShop.presentation.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler


class ScaleLayoutManager : LinearLayoutManager {
  private val mShrinkAmount = 0.17f
  private val mShrinkDistance = 1.9f

  constructor(context: Context?) : super(context) {}
  constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
    context,
    orientation,
    reverseLayout
  ) {
  }

  override fun scrollHorizontallyBy(dx: Int, recycler: Recycler, state: RecyclerView.State): Int {
    val orientation = orientation
    return if (orientation == HORIZONTAL) {
      val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
      val midpoint = width / 2f
      val d0 = 0f
      val d1 = mShrinkDistance * midpoint
      val s0 = 1f
      val s1 = 1f - mShrinkAmount
      for (i in 0 until childCount) {
        val child: View = getChildAt(i)!!
        val childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
        val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
        val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
        child.setScaleX(scale)
        child.setScaleY(scale)
      }
      scrolled
    } else {
      0
    }
  }
}