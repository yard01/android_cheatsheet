package com.github.yard01.sandbox.navigator_example.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.yard01.sandbox.navigator_example.R

class SendFragment : Fragment() {

    private lateinit var sendViewModel: SendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sendViewModel =
            ViewModelProviders.of(this).get(SendViewModel::class.java)
        val root = inflater.inflate(R.layout.ui_navigator_fragment_send, container, false)
        val textView: TextView = root.findViewById(R.id.ui_navigator_text_send)
        sendViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}