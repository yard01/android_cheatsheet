package com.nikhilpanju.fabfilter.filter

import android.content.Context
import android.util.AttributeSet
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
import com.nikhilpanju.fabfilter.R

class FilterSeekbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : CrystalRangeSeekbar(context, attrs, defStyleAttr) {

    override fun getThumbWidth(): Float = resources.getDimension(R.dimen.ui_fabfilter_thumb_size)
    override fun getThumbHeight(): Float = resources.getDimension(R.dimen.ui_fabfilter_thumb_size)
    override fun getBarHeight(): Float = thumbHeight / 4.5f
}
