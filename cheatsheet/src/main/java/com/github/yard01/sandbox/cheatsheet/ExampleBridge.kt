package com.github.yard01.sandbox.cheatsheet

import android.content.Context
import android.graphics.Bitmap
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.InfoProvider

interface ExampleBridge: IconProvider, InfoProvider {
    /*
    var link: String
    fun loadDescription(): String
    fun loadInfo(): String
    fun loadIcon(context: Context): Bitmap*/
    fun executeExample()
}