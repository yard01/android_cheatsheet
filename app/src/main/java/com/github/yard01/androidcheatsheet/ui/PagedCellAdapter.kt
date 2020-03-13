package com.github.yard01.androidcheatsheet.ui

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import kotlinx.android.synthetic.main.example_cell.view.*

class PagedCellAdapter (diffCallback: DiffUtil.ItemCallback<CheatSheetExampleCell>) :
    PagedListAdapter<CheatSheetExampleCell, PagedCellAdapter.CellViewHolder>(diffCallback) {
    companion object {
        val BUFFER_SIZE = 4
        fun clickCell(view: View, cell: CheatSheetExampleCell) {
            val page: ExamplePageFragment = ExamplePageFragment(cell)
            //view.context.
            (view.context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.cheatsheet_container, page)
                .addToBackStack(null)
                .commit()
            //Log.d("celladapder", ""+(view.context as FragmentActivity).supportFragmentManager)
        }
    }

    inner class CellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.icon_imageView
        val info: TextView = itemView.info_textView
    }

    val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(BUFFER_SIZE)
        .build()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.example_cell, parent, false)
        return CellViewHolder(view)
    }


    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val cell = this.getItem(position)
        if (cell != null) {
            holder.icon.setImageDrawable(cell?.bridge?.getIcon())
            holder.info.text = cell?.bridge?.getInfo()
            holder.itemView.setOnClickListener { view -> clickCell(view, cell) }
        }
    }

}