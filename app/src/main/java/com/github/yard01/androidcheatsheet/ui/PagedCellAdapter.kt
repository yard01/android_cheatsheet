package com.github.yard01.androidcheatsheet.ui

import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow

class PagedCellAdapter (diffCallback: DiffUtil.ItemCallback<CheatSheetExampleCell>) :
    PagedListAdapter<CheatSheetExampleCell, PagedRowAdapter.RowViewHolder>(diffCallback) {
    companion object {
        val BUFFER_SIZE = 4
    }
    val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(BUFFER_SIZE )
        .build()

    // .setEnablePlaceholders(false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagedRowAdapter.RowViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: PagedRowAdapter.RowViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(
        holder: PagedRowAdapter.RowViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)

    }
}