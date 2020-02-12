package com.github.yard01.sandbox.cheatsheet

import android.content.Context
import android.graphics.Bitmap

interface ExampleBridge {
    /*
    var link: String
    fun loadDescription(): String
    fun loadInfo(): String
    fun loadIcon(): Bitmap*/
    fun executeExample(context: Context)
}