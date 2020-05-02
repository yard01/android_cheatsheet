package com.github.yard01.sensor_example_bridges

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.sensor_list_layout.*
import kotlinx.android.synthetic.main.sensor_list_layout.view.*

class SensorListFragment: Fragment() {
    private fun listSensor(view: View) {
        val smm: SensorManager = this.context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager;
        //lv = (ListView) findViewById (R.id.listView1);
        val sensor_list: List<Sensor> = smm.getSensorList(Sensor.TYPE_ALL);
        view.sensor_list_ListView.adapter = ArrayAdapter<Sensor>(
            this.context as Context,
            android.R.layout.simple_list_item_1,
            sensor_list
        ) //(ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,  sensor_list_ListView));
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sensor_list_layout, container,false)
        view.sensorList_TextView.text = this.context?.getString(R.string.sensorlist_example_name)
        listSensor(view)
        return view
    }
}