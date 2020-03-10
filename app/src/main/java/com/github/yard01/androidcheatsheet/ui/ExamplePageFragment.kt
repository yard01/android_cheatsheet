package com.github.yard01.androidcheatsheet.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import kotlinx.android.synthetic.main.example_page_fragment.view.*

class ExamplePageFragment(val cell: CheatSheetExampleCell): Fragment() {

    class ScreenshotAdapter(val bridge: ExampleBridge): RecyclerView.Adapter<ScreenshotAdapter.ScreenshotHolder>() {

        inner class ScreenshotHolder(var rowView: View): RecyclerView.ViewHolder(rowView) {
            //val title: TextView = rowView.rowTitle_TextView
            //val pager = rowView.cells_viewPager

            //val contentView: TextView = rowView.content
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ScreenshotAdapter.ScreenshotHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            //this.cell.
            //bridge.
            return (bridge as ScreenshotProvider).getScreenshotCount()
        }

        override fun onBindViewHolder(holder: ScreenshotAdapter.ScreenshotHolder, position: Int) {
            val drawable = (bridge as ScreenshotProvider).getScreenshot(position)
            
        }

    }

    fun clickRun(view: View) {
       this.cell.bridge.executeExample()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result: View = inflater.inflate(R.layout.example_page_fragment, container, false)
        val runButton: Button = result.run_example_Button// .findViewById(R.id.run_example_Button)
        runButton.setOnClickListener { view -> clickRun(view) }

        val layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val screensotRecyclerView = result.screenshot_list_RecyclerView
        screensotRecyclerView.layoutManager = layoutManager

        return result
    }
}