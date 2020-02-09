package com.github.yard01.androidcheatsheet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import kotlinx.android.synthetic.main.cheatsheet_content.*
import kotlinx.android.synthetic.main.cheatsheet_content.view.*

class CheatSheetFragment: Fragment() {

    inner class DiffUtilCallbak: DiffUtil.ItemCallback<CheatSheetExampleRow>() {
        override fun areItemsTheSame(//сравнивает идентификаторы строк
            oldItem: CheatSheetExampleRow,
            newItem: CheatSheetExampleRow
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(//сравнивает содержимое строк
            oldItem: CheatSheetExampleRow,
            newItem: CheatSheetExampleRow
        ): Boolean {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val result: View = inflater.inflate(R.layout.cheatsheet_fragment, container, false)
        result.examplerow_list.adapter = PagedRowAdapter(DiffUtilCallbak())
        return result
    }
}