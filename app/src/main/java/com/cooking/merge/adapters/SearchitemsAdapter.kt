package com.cooking.merge.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.R
import com.cooking.merge.SearchDetailsActivity
import kotlinx.android.synthetic.main.cardview_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class SearchitemsAdapter(private var itemList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    //此處加上Filterable的extensions就可以自行定義filter（getFilter()）

    var searchList = ArrayList<String>()

    lateinit var mcontext: Context

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        searchList = itemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemListView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout, parent, false)
        val source = ListHolder(itemListView)
        mcontext = parent.context
        return source
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.title.setTextColor(Color.BLACK)
        holder.itemView.title.text = searchList[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(mcontext, SearchDetailsActivity::class.java)
            intent.putExtra("passhot", searchList[position])
            mcontext.startActivity(intent)
            Log.d("Selected:", searchList[position])
        }
    }


    override fun getFilter(): Filter {  //使用getFilter()時有兩個必須有的method: performFiltering 及 publishResults
        return object : Filter() {

            //The performFiltering method checks if we have typed a text in the SearchView.
            //If there is not any text, will return all items.
            //If there is a text, then we check if the characters match the items from the list and return the results in a FilterResults type.

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    searchList = itemList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in itemList) {
                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    searchList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = searchList
                return filterResults
            }

            //The publishResults get these results, passes it to the countryFilterList array and updates the RecyclerView.
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                searchList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}