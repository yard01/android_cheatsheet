package com.github.yard01.sandbox.cheatsheet

import android.content.Context
import android.graphics.Bitmap
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider

interface ExampleBridge: IconProvider {
    /*
    var link: String
    fun loadDescription(): String
    fun loadInfo(): String
    fun loadIcon(context: Context): Bitmap*/
    fun executeExample()
}