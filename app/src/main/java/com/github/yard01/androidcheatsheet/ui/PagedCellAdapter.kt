package com.github.yard01.androidcheatsheet.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import kotlinx.android.synthetic.main.example_row.view.*

class PagedCellAdapter (diffCallback: DiffUtil.ItemCallback<CheatSheetExampleCell>) :
    PagedListAdapter<CheatSheetExampleCell, PagedCellAdapter.CellViewHolder>(diffCallback) {
    companion object {
        val BUFFER_SIZE = 4
    }

    inner class CellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(BUFFER_SIZE )
        .build()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.example_cell, parent, false)
        return CellViewHolder(view)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        var cell = this.getItem(position)

    }

}