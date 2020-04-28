package com.github.yard01.sandbox.sensor_cheatsheet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.yard01.sensor_cheatsheet.R

class SensorCheatSheetFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val result: View = inflater.inflate(R.layout.sensorcheatsheet_fragment /*fragment*/, container, false)
        return result
    }
}