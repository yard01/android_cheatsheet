package com.github.yard01.androidcheatsheet.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.MainThreadExecutor
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import io.reactivex.Observable
import kotlinx.android.synthetic.main.example_row.view.*
import java.util.concurrent.Executors

class PagedRowAdapter(diffCallback: DiffUtil.ItemCallback<CheatSheetExampleRow>) :
    PagedListAdapter<CheatSheetExampleRow, PagedRowAdapter.RowViewHolder>(diffCallback) {

    init {

    }
    inner class RowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

        val titleTextView: TextView = itemView.rowTitle_TextView
        val pager = itemView.cells_viewPager
        init {
            pager.layoutManager = layoutManager
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.example_row, parent, false)
        return RowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val row = this.getItem(position)
        holder.titleTextView.text = row?.title  //holder.itemView.context.getString(CheatSheetViewModel.exampleRows[position].titleId)
        val adapter = PagedCellAdapter(CheatSheetFragment.CellDiffUtilCallback())


        val pagedList: PagedList<CheatSheetExampleCell> = PagedList(row?.cellSource as DataSource<Int, CheatSheetExampleCell>,
            CheatSheetFragment.paggingConfig,
            Executors.newSingleThreadExecutor(),
            MainThreadExecutor()
        )

        Observable.just(pagedList).subscribe(adapter::submitList)
        holder.pager.adapter = adapter

    }

}