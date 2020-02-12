package com.github.yard01.androidcheatsheet.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import kotlinx.android.synthetic.main.example_row.view.*


class PagedRowAdapter(diffCallback: DiffUtil.ItemCallback<CheatSheetExampleRow>) :
    PagedListAdapter<CheatSheetExampleRow, PagedRowAdapter.RowViewHolder>(diffCallback) {

    inner class RowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.rowTitle_TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.example_row, parent, false)
        return RowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val row = this.getItem(position)

        holder.titleTextView.text = "" + position  //holder.itemView.context.getString(CheatSheetViewModel.exampleRows[position].titleId)
    }

}