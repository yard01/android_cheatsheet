package com.github.yard01.integration_example

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class IntegrationActivity : AppCompatActivity() {
    companion object {
        val FRAGMENT_CLASSNAME = "fragment_classname"
        //var context: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integration)
        //context = this
        val exampleFragment = this.javaClass.classLoader?.loadClass(this.intent.getStringExtra(FRAGMENT_CLASSNAME))?.newInstance() as Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.integration_example_container, exampleFragment)
            .commit()

    }
}
