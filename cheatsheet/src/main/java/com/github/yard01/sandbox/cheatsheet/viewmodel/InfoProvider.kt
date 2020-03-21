package com.github.yard01.sandbox.cheatsheet.viewmodel

import android.content.Context

interface InfoProvider {
    fun getName(): String
    fun getDescription(): String
}