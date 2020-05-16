package com.github.yard01.androidcheatsheet.ui

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.preference.PreferenceManager
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


    class RowDiffUtilCallback: DiffUtil.ItemCallback<CheatSheetExampleRow>() {
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

    class CellDiffUtilCallback: DiffUtil.ItemCallback<CheatSheetExampleCell>() {
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

    var adapter: PagedRowAdapter? = null //PagedRowAdapter(RowDiffUtilCallback())
    var fab: FloatingActionButton? = null


    private fun createExampleList(view: View) {
        var provider = MainActivity.currentFactory?.createProvider()
        provider?.provide()

        adapter = PagedRowAdapter(RowDiffUtilCallback())
        val pagedList: PagedList<CheatSheetExampleRow> = PagedList(CheatSheetViewModel.contentDataSource as DataSource<Int, CheatSheetExampleRow>,
            paggingConfig,
            Executors.newSingleThreadExecutor(),
            MainThreadExecutor())
        Observable.just(pagedList).subscribe(adapter!!::submitList)
        view.examplerow_list.adapter = adapter
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //handleIntent(this.activity?.intent) //Search
        val result: View = inflater.inflate(R.layout.cheatsheet_fragment, container, false)
        if (activity is CheatSheetContentActivity)
            (activity as CheatSheetContentActivity).setSupportActionBar(result.findViewById(R.id.toolbar))

        setHasOptionsMenu(true);

        fab = result.preferences_FAB
        createExampleList(result)
        hideFab()

        fab?.setOnClickListener { button -> run {
            CheatSheetViewModel.filter = ""
            CheatSheetViewModel.search = ""
            PreferenceManager
                .getDefaultSharedPreferences(this.activity)
                .edit()
                .remove(this.getString(R.string.cheatsheet_text_filter_Key))
                .commit()

            button.visibility = View.INVISIBLE
            //CheatSheetViewModel.contentDataSource?.invalidate()
            createExampleList(result)
        } }

        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setHasOptionsMenu(true);
    }

    private fun hideFab() {
        if ("".equals(CheatSheetViewModel.filter))
            fab?.visibility = View.INVISIBLE
        else
            fab?.visibility = View.VISIBLE
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = this.activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.activity?.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) view = View(this.activity)

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_cheat_sheet_content, menu);
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            this.setOnQueryTextFocusChangeListener {view, focus  -> run {
                    if (!focus && "".equals((view as SearchView).query.toString()) ) {
                        CheatSheetViewModel.search = ""
                        (view as SearchView).setQuery(CheatSheetViewModel.search, true)
                        hideFab()
                        adapter?.notifyDataSetChanged()
                    }
                    else
                        (view as SearchView).setQuery(CheatSheetViewModel.search, false)
                }
            }
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                private fun searchText(query: String?): Boolean {
                    if (query != null) {
                        CheatSheetViewModel.search = query
                        adapter?.notifyDataSetChanged()
                        return true
                    }
                    return false
                }
                override fun onQueryTextSubmit(query: String?): Boolean {
                    hideKeyboard()
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return searchText(query)
                }
            })


        }

    }


    private fun openPreferences() {
        val preferencesFragment = PreferencesFragment()
        //show preferences
        (activity as AppCompatActivity).getSupportFragmentManager().beginTransaction() //
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
/*
    private fun handleIntent(intent: Intent?) {
        if (intent == null) return;
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            CheatSheetViewModel.search = query
        }
    }
*/
}

private fun SearchView.setOnCloseListener(function: () -> Unit) {

}
