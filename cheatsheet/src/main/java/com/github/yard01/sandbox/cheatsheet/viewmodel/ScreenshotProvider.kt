package com.github.yard01.sandbox.cheatsheet.viewmodel

import android.graphics.drawable.Drawable

interface ScreenshotProvider {
    fun getScreenshotCount(): Int;
    //fun getScreenshot(index: Int): Drawable?
    fun getScreenshotId(index: Int): Int

}