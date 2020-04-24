package com.github.yard01.androidcheatsheet.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.androidcheatsheet.ui.richtext.RichText
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import kotlinx.android.synthetic.main.example_page_fragment.view.*
import kotlinx.android.synthetic.main.screenshot.view.*

class ExamplePageFragment(val cell: CheatSheetExampleCell): Fragment() {

    class ScreenshotAdapter(val bridge: ExampleBridge): RecyclerView.Adapter<ScreenshotAdapter.ScreenshotHolder>() {

        inner class ScreenshotHolder(var view: View): RecyclerView.ViewHolder(view) {
            val screenshot = view.screenshot_ImageView
            init {
                screenshot.setOnClickListener { view -> clickImage(view) }
            }

            fun clickImage(view: View) {
                val intent = Intent(view.context, FullscreenActivity::class.java).apply {
                    putExtra(DRAWABLE_ID, view.tag as Int)
                }
                view.context.startActivity(intent)
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
            return (bridge as ScreenshotProvider).getScreenshotCount()
        }

        override fun onBindViewHolder(holder: ScreenshotAdapter.ScreenshotHolder, position: Int) {
            holder.screenshot.setImageResource((bridge as ScreenshotProvider).getScreenshotId(position))
            holder.screenshot.tag = (bridge as ScreenshotProvider).getScreenshotId(position)
        }

    }

    fun clickRun(view: View) {
        if (this.cell != null)
            this.cell.bridge.executeExample()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result: View = inflater.inflate(R.layout.example_page_fragment, container, false)
        val runButton: Button = result.run_example_Button// .findViewById(R.id.run_example_Button)
        runButton.text = this.getString(R.string.example_run_button)
        var html: Spanned? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            html = Html.fromHtml(
                this.cell.bridge.getDescription(),
                Html.FROM_HTML_MODE_COMPACT)
         else
            html = Html.fromHtml(
                this.cell.bridge.getDescription()
            )
        result.example_description_TextView.text = RichText.highlightText(SpannableString(html), CheatSheetViewModel.search, Color.YELLOW)

        //RichText.highlightText(cell?.bridge?.getName(), CheatSheetViewModel.search, Color.YELLOW)

        result.example_description_TextView.movementMethod = LinkMovementMethod.getInstance()

        runButton.setOnClickListener { view -> clickRun(view) }

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
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