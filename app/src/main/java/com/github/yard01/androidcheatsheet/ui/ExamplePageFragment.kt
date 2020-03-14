package com.github.yard01.androidcheatsheet.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.yard01.androidcheatsheet.CheatSheetContentActivity
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import kotlinx.android.synthetic.main.example_page_fragment.view.*
import kotlinx.android.synthetic.main.screenshot.view.*

class ExamplePageFragment(val cell: CheatSheetExampleCell): Fragment() {
    //var cell: CheatSheetExampleCell? = null

    //constructor(_cell: CheatSheetExampleCell): this() {
    //    cell = _cell
    //}

    class ScreenshotAdapter(val bridge: ExampleBridge): RecyclerView.Adapter<ScreenshotAdapter.ScreenshotHolder>() {

        inner class ScreenshotHolder(var view: View): RecyclerView.ViewHolder(view) {
            val screenshot = view.screenshot_ImageView
            init {
                screenshot.setOnClickListener { view -> clickImage(view) }
            }
            fun clickImage(view: View) {
                val fullScreenView = ScreenshotFragment((view as ImageView).drawable)
                //view.context.
                (view.context as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.cheatsheet_container, fullScreenView, CheatSheetContentActivity.FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit()

                //screenshot.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ScreenshotAdapter.ScreenshotHolder {
            val view: View = LayoutInflater.from(parent.context) //в родительское окно встраивается View с LinearLayout, на котором лежат визуальные элементы строки
                .inflate(com.github.yard01.androidcheatsheet.R.layout.screenshot,
                    parent,
                    false)
            return ScreenshotHolder(view)
        }

        override fun getItemCount(): Int {
            //this.cell.
            //bridge.
            return (bridge as ScreenshotProvider).getScreenshotCount()
        }

        override fun onBindViewHolder(holder: ScreenshotAdapter.ScreenshotHolder, position: Int) {
            val drawable = (bridge as ScreenshotProvider).getScreenshot(position)
            holder.screenshot.setImageDrawable(drawable)
        }

    }

    fun clickRun(view: View) {
        if (this.cell != null)
            this.cell!!.bridge.executeExample()
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
        if (this.cell != null)
            screensotRecyclerView.adapter = ScreenshotAdapter(this.cell!!.bridge)
        return result
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true);
    }
}