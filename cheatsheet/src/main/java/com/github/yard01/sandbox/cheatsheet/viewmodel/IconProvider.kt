package com.github.yard01.sandbox.cheatsheet.viewmodel

import android.content.Context
import android.graphics.Bitmap

interface IconProvider {
    fun getIcon(context: Context): Bitmap
}