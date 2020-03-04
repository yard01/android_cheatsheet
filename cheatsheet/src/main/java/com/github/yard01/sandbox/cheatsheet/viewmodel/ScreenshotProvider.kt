package com.github.yard01.sandbox.cheatsheet.viewmodel

import android.graphics.drawable.Drawable

interface ScreenshotProvider {
    fun getScreenshots(): Array<Drawable>
}