package com.github.yard01.androidcheatsheet.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.github.yard01.androidcheatsheet.CheatSheetContentActivity
import com.github.yard01.androidcheatsheet.MainActivity
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.MainThreadExecutor
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.cheatsheet_content.view.*
import kotlinx.android.synthetic.main.cheatsheet_fragment.*
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
        val result: View = inflater.inflate(R.layout.cheatsheet_content /*_fragment*/, container, false)

        val adapter = PagedRowAdapter(RowDiffUtilCallbak())

        //setSupportActionBar(toolbar)

        var provider = MainActivity.currentFactory?.createProvider()
        provider?.provide()

        val pagedList: PagedList<CheatSheetExampleRow> = PagedList(CheatSheetViewModel.contentDataSource as DataSource<Int, CheatSheetExampleRow>,
            paggingConfig,
            Executors.newSingleThreadExecutor(),
            MainThreadExecutor())

        Observable.just(pagedList).subscribe(adapter::submitList)
        //CheatSheetViewModel.rows?.subscribe(adapter::submitList)
        result.examplerow_list.adapter = adapter
        //setHasOptionsMenu(true);
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setHasOptionsMenu(true);
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_cheat_sheet_content, menu);
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }

    override fun onDetach() {
        super.onDetach()

    }

}