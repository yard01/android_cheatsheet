package com.github.yard01.androidcheatsheet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell

class ExamplePageFragment(val cell: CheatSheetExampleCell): Fragment() {

    fun clickRun(view: View) {
       this.cell.bridge.executeExample()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result: View = inflater.inflate(R.layout.example_page_fragment, container, false)
        result.setOnClickListener { view -> clickRun(view) }
        return result
    }
}