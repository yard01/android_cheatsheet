package com.github.yard01.integration_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.yard01.integration_example_bridges.R

class IntegrationActivity : AppCompatActivity() {
    companion object {
        val FRAGMENT_CLASSNAME = "classname"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integration)
    }
}
