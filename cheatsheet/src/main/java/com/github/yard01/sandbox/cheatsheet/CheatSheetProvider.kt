package com.github.yard01.sandbox.cheatsheet

import android.content.Context
import androidx.fragment.app.Fragment

interface CheatSheetProvider  {
    val context: Context
    val fragment: Fragment
}