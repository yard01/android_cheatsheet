package com.github.yard01.androidcheatsheet.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
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
        handleIntent(this.activity?.intent)
        val result: View = inflater.inflate(R.layout.cheatsheet_fragment, container, false)

        val adapter = PagedRowAdapter(RowDiffUtilCallbak())

        (activity as CheatSheetContentActivity).setSupportActionBar(result.findViewById(R.id.toolbar))

        var provider = MainActivity.currentFactory?.createProvider()
        provider?.provide()

        val pagedList: PagedList<CheatSheetExampleRow> = PagedList(CheatSheetViewModel.contentDataSource as DataSource<Int, CheatSheetExampleRow>,
            paggingConfig,
            Executors.newSingleThreadExecutor(),
            MainThreadExecutor())

        Observable.just(pagedList).subscribe(adapter::submitList)
        result.examplerow_list.adapter = adapter
        setHasOptionsMenu(true);


        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setHasOptionsMenu(true);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_cheat_sheet_content, menu);
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {

            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onDetach() {
        super.onDetach()

    }

    private fun handleIntent(intent: Intent?) {
        if (intent == null) return;
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            CheatSheetViewModel.search = query
            //Log.d("searchquery", query)

            //use the query to search your data somehow
        }
    }

}