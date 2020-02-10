package com.github.yard01.androidcheatsheet.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import kotlinx.android.synthetic.main.example_row.view.*

class RowAdapter: RecyclerView.Adapter<RowAdapter.RowViewHolder>() {

    inner class RowViewHolder(var rowView: View): RecyclerView.ViewHolder(rowView) {
        val title: TextView = rowView.rowTitle_TextView

        //val contentView: TextView = rowView.content
    }
/*
    inner class RowsDiffUlitlCallback: DiffUtil.ItemCallback<CheatSheetExampleRow>() {
        override fun areItemsTheSame(
            oldItem: CheatSheetExampleRow,
            newItem: CheatSheetExampleRow
        ): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun areContentsTheSame(
            oldItem: CheatSheetExampleRow,
            newItem: CheatSheetExampleRow
        ): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
 */
    /*
    internal class WeatherDiffUtilCallback :
        DiffUtil.ItemCallback<WeatherForecastEntity>() {
        override fun areItemsTheSame(@NonNull oldItem: WeatherForecastEntity, @NonNull newItem: WeatherForecastEntity): Boolean { //id control
            return oldItem.getDate().equals(newItem.getDate())
        }

        override fun areContentsTheSame(@NonNull oldItem: WeatherForecastEntity, @NonNull newItem: WeatherForecastEntity): Boolean { //content control
            return oldItem.getMaxTemperature() === newItem.getMaxTemperature()
        }
    }*/

    override fun getItemCount(): Int = CheatSheetViewModel.exampleRows.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val view: View = LayoutInflater.from(parent.context) //в родительское окно встраивается View с LinearLayout, на котором лежат визуальные элементы строки
                .inflate(com.github.yard01.androidcheatsheet.R.layout.example_row,
                         parent,
                         false)
        return RowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        holder.title.text = holder.rowView.context.getString(CheatSheetViewModel.exampleRows[position].titleId)
    }
}