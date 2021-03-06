package com.github.yard01.androidcheatsheet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.github.yard01.androidcheatsheet.ui.CheatSheetFragment
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.cheatsheetregister.CheatSheetRegister
import kotlinx.android.synthetic.main.cheatsheet_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*

class MainActivity : AppCompatActivity() {
    //var items: Array<String>

    var factories: Array<CheatSheetProviderFactory> = arrayOf()
    var preferences: SharedPreferences? = null
    init {

        //items = arrayOfNulls<>()
        //    resources.getStringArray(R.array.cheatsheet_list)

    }

    companion object {
        var currentFactory : CheatSheetProviderFactory? = null

    }

    private fun createContent() {
        val factoryNames = resources.getStringArray(R.array.cheatsheet_list)
        factories =  Array(factoryNames.size, {i-> CheatSheetRegister.getProviderFactory(this, factoryNames[i])})
        this.cheatsheet_list.adapter = CheatSheetItemAdapter()
    }

    private fun clickItem(view: View) {
        currentFactory = view.tag as CheatSheetProviderFactory
        if (cheatsheet_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            //twoPane = true
            var fragment = CheatSheetFragment()
            supportFragmentManager.beginTransaction().replace(
                R.id.cheatsheet_container,
                fragment//provider!!.fragment
            ).commit()
        } else {
            val intent = Intent(this, CheatSheetContentActivity::class.java).apply {
                putExtra(CheatSheetContentActivity.PROVIDER_ID_PARAMETER, currentFactory?.providerClassName)
            }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        CheatSheetViewModel.filter = "" + preferences?.getString(this.getString(R.string.cheatsheet_text_filter_Key), "")
        setContentView(R.layout.cheatsheet_list)
        createContent()
    }

    inner class CheatSheetItemAdapter (): RecyclerView.Adapter<CheatSheetItemAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val idView: TextView = itemView.id_text
            val contentView: TextView = itemView.content
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = factories.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val factory = factories[position]
            holder.idView.text = (position + 1).toString()
            holder.contentView.text = factory.descriptionString
            holder.itemView.tag = factory
            holder.itemView.setOnClickListener({v -> clickItem(v)})
        }
    }

}
