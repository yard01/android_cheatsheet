package com.github.yard01.androidcheatsheet.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.yard01.androidcheatsheet.R
import kotlinx.android.synthetic.main.screenshot_fullscreen.view.*

class ScreenshotFragment(val drawable: Drawable): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val result: View = inflater.inflate(R.layout.screenshot_fullscreen, container, false)
        val image = result.fullscreen_ImageView
        image.setImageDrawable(drawable)
        return result
    }
}