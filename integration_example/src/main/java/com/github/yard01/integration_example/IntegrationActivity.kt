package com.github.yard01.integration_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.yard01.integration_example_bridges.R

class IntegrationActivity : AppCompatActivity() {
    companion object {
        val FRAGMENT_CLASSNAME = "fragment_classname"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integration)
        val exampleFragment = this.javaClass.classLoader?.loadClass(this.intent.getStringExtra(FRAGMENT_CLASSNAME))?.newInstance() as Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.integration_example_container, exampleFragment)
            .commit()

    }
}
