package com.github.yard01.sandbox.cheatsheet.viewmodel

import android.content.Context
import android.graphics.Bitmap
import kotlin.reflect.KFunction

interface IconProvider {
    fun getIcon(): Bitmap
}