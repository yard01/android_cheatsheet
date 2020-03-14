package com.github.yard01.androidcheatsheet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.DataSource
import androidx.recyclerview.widget.DiffUtil
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import kotlinx.android.synthetic.main.cheatsheet_content.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.yard01.androidcheatsheet.MainActivity
import com.github.yard01.sandbox.cheatsheet.MainThreadExecutor
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import io.reactivex.Observable
import kotlinx.android.synthetic.main.cheatsheet_content.view.*
import java.util.concurrent.Executors

class CheatSheetFragment: Fragment() {
    companion object {
        val ROW_BUFFER_SIZE= 5;

        val paggingConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ROW_BUFFER_SIZE)
            .build();
    }


    class RowDiffUtilCallbak: DiffUtil.ItemCallback<CheatSheetExampleRow>() {
        override fun areItemsTheSame(//сравнивает идентификаторы строк
            oldItem: CheatSheetExampleRow,
            newItem: CheatSheetExampleRow
        ): Boolean {
            return oldItem.title.equals(newItem.title)
        }

        override fun areContentsTheSame(//сравнивает содержимое строк
            oldItem: CheatSheetExampleRow,
            newItem: CheatSheetExampleRow
        ): Boolean {
            return oldItem.title.equals(newItem.title)
        }

    }

    class CellDiffUtilCallbak: DiffUtil.ItemCallback<CheatSheetExampleCell>() {
        override fun areItemsTheSame(//сравнивает идентификаторы строк
            oldItem: CheatSheetExampleCell,
            newItem: CheatSheetExampleCell
        ): Boolean {
            return oldItem.info.equals(newItem.info)
        }

        override fun areContentsTheSame(//сравнивает содержимое строк
            oldItem: CheatSheetExampleCell,
            newItem: CheatSheetExampleCell
        ): Boolean {
            return oldItem.info.equals(newItem.info)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val result: View = inflater.inflate(R.layout.cheatsheet_fragment, container, false)

        val adapter = PagedRowAdapter(RowDiffUtilCallbak())

        var provider = MainActivity.currentFactory?.createProvider()
        provider?.provide()

        val pagedList: PagedList<CheatSheetExampleRow> = PagedList(CheatSheetViewModel.contentDataSource as DataSource<Int, CheatSheetExampleRow>,
            paggingConfig,
            Executors.newSingleThreadExecutor(),
            MainThreadExecutor())

        Observable.just(pagedList).subscribe(adapter::submitList)

        //CheatSheetViewModel.rows?.subscribe(adapter::submitList)

        result.examplerow_list.adapter = adapter

        return result
    }

    override fun onDetach() {
        super.onDetach()

    }
}