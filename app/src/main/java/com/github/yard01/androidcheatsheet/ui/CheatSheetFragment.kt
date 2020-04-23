package com.github.yard01.androidcheatsheet.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.Observable
import kotlinx.android.synthetic.main.cheatsheet_content.view.*
import kotlinx.android.synthetic.main.cheatsheet_fragment.view.*
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

    val adapter = PagedRowAdapter(RowDiffUtilCallbak())
    var fab: FloatingActionButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        handleIntent(this.activity?.intent)
        val result: View = inflater.inflate(R.layout.cheatsheet_fragment, container, false)
        fab = result.preferences_FAB

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
        result.setOnFocusChangeListener { view, b -> run {Log.d("search", "fragment focus")} }

        fab?.setOnClickListener { button -> run {
            CheatSheetViewModel.search = ""
            CheatSheetViewModel.filter = ""
            button.visibility = View.INVISIBLE
            adapter.notifyDataSetChanged()
//            val preferencesFragment = PreferencesFragment()
//            //show preferences
//            (activity as CheatSheetContentActivity).getSupportFragmentManager().beginTransaction() //
//                .replace(
//                    R.id.cheatsheet_container,
//                    preferencesFragment
//                ).addToBackStack(null)
//                .commit()
        } }
        hideFab()
        //Log.d("createview", "create view")
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setHasOptionsMenu(true);
    }

    private fun hideFab() {
        if ("".equals(CheatSheetViewModel.search) && "".equals(CheatSheetViewModel.filter))
            fab?.visibility = View.INVISIBLE
        else
            fab?.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_cheat_sheet_content, menu);
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            //this.setQuery(CheatSheetViewModel.search, false)
            //this.setOnCloseListener(SearchClose())
            //this.setOnQueryTextListener(SearchView.onQu)
            //this.setOnSearchClickListener {v -> run { Log.d("searchclick", " click! ") } }

            //val closeButton: ImageView? = this.findViewById(R.id.search_close_btn)
            //this.
            //closeButton?.setOnClickListener { button -> run {Log.d("searchclose", "search close click!")} }

            this.setOnQueryTextFocusChangeListener {view, focus  -> run {
                    if (!focus && "".equals((view as SearchView).query.toString()) ) {
                        CheatSheetViewModel.search = ""
                        (view as SearchView).setQuery(CheatSheetViewModel.search, true)
                        hideFab()
                        adapter.notifyDataSetChanged()
                    }
                    else
                        (view as SearchView).setQuery(CheatSheetViewModel.search, false)
                    //Log.d("searchfocus", " " + v + " : " + b + " : " + (v as SearchView).query)

                }
            }

        }

    }


    private fun openPreferences() {
        val preferencesFragment = PreferencesFragment()
        //show preferences
        (activity as CheatSheetContentActivity).getSupportFragmentManager().beginTransaction() //
            .replace(
                R.id.cheatsheet_container,
                preferencesFragment
            ).addToBackStack(null)
            .commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) openPreferences()
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
        }
    }

}

private fun SearchView.setOnCloseListener(function: () -> Unit) {

}
