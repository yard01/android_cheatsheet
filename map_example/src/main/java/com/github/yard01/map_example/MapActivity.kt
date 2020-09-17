package com.github.yard01.map_example
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        val FRAGMENT_CLASSNAME = "fragment_classname"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_layout)
    }

    override fun onMapReady(p0: GoogleMap?) {
        //TODO("Not yet implemented")
    }
}