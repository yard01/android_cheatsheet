package com.github.yard01.map_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {
    companion object {
        val FRAGMENT_CLASSNAME = "fragment_classname"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_layout)
    }
}