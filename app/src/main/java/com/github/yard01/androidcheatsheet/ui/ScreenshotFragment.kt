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
//class ScreenshotFragment(): Fragment() {

//    companion object {
//        fun newInstance() = ScreenshotFragment()
//    }
//    var drawable: Drawable? = null

//    constructor(_drawable: Drawable): this() {
//        drawable =  _drawable
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val result: View = inflater.inflate(R.layout.screenshot_fullscreen, container, false)
        val imageView = result.fullscreen_ImageView
        if (drawable != null)
            imageView.setImageDrawable(drawable)
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true);
    }
}