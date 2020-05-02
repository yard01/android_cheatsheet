package com.github.yard01.sensor_examples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.yard01.sensor_example_bridges.R

class SensorActivity : AppCompatActivity() {
    companion object {
        val FRAGMENT_CLASSNAME = "fragment_classname"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)
        val exampleFragment = this.javaClass.classLoader?.loadClass(this.intent.getStringExtra(FRAGMENT_CLASSNAME))?.newInstance() as Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.sensor_example_container, exampleFragment)
            .commit()

        //Log.d("bundle",  ""+this.intent.getStringExtra(FRAGMENT_CLASSNAME))

    }
}
