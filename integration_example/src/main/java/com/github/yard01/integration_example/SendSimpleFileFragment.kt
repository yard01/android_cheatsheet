package com.github.yard01.integration_example

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.yard01.integration_example_bridges.R
import kotlinx.android.synthetic.main.fragment_send_simple_file.view.*

class SendSimpleFileFragment: Fragment() {

    fun createContent(view: View) {
        view.sendTXT_Button.setOnClickListener{button -> run {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
                
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_send_simple_file, container,false)
        //view.sensorList_TextView.text = this.context?.getString(R.string.sensorlist_example_name)
        createContent(view)
        return view
    }
}