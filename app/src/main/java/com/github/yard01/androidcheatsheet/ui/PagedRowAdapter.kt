package com.github.yard01.androidcheatsheet.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.paging.PositionalDataSource
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.MainThreadExecutor
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.example_row.view.*
import java.util.concurrent.Executors


class PagedRowAdapter(diffCallback: DiffUtil.ItemCallback<CheatSheetExampleRow>) :
    PagedListAdapter<CheatSheetExampleRow, PagedRowAdapter.RowViewHolder>(diffCallback) {

    inner class RowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.rowTitle_TextView
        val pager: ViewPager2 = itemView.cells_viewPager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.example_row, parent, false)
        return RowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val row = this.getItem(position)
        holder.titleTextView.text = row?.title  //holder.itemView.context.getString(CheatSheetViewModel.exampleRows[position].titleId)
        val adapter = PagedCellAdapter(CheatSheetFragment.CellDiffUtilCallbak())


        val pagedList: PagedList<CheatSheetExampleCell> = PagedList(row?.cellSource as DataSource<Int, CheatSheetExampleCell>,
            CheatSheetFragment.paggingConfig,
            Executors.newSingleThreadExecutor(),
            MainThreadExecutor()
        )

        Observable.just(pagedList).subscribe(adapter::submitList)
        holder.pager.adapter = adapter

    }

}