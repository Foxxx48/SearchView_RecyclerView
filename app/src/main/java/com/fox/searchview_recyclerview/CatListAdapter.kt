package com.fox.searchview_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fox.searchview_recyclerview.databinding.ItemCatsBinding
import java.util.*

class CatListAdapter(var catsList: ArrayList<Cat>): RecyclerView.Adapter<CatListAdapter.CatListHolder>() {

    lateinit var context: Context


    fun filterList(filterList: ArrayList<Cat>) {

        catsList = filterList

        notifyDataSetChanged()
    }

    inner class CatListHolder(val binding: ItemCatsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListHolder {
        val binding = ItemCatsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CatListHolder(binding)
    }

    override fun onBindViewHolder(holder: CatListHolder, position: Int) {
//        val cats = catsList[position]
        with(holder) {
            with(catsList[position]) {
                binding.tvCatName.text = name
                binding.tvCatAge.text = age.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return catsList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    fun filter(text: String?) {
        // creating a new array list to filter our data.
        val filteredlist: java.util.ArrayList<Cat> = java.util.ArrayList<Cat>()

        // running a for loop to compare elements.
        for (item in catsList) {
            // checking if the entered string matched with any item of our recycler view.
            if (text != null) {
                if (item.name.toLowerCase().contains(text.lowercase(Locale.getDefault())) or item.age.toString().contains(text.lowercase(Locale.getDefault()))) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredlist.add(item)
                }
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(context,"No data found", Toast.LENGTH_SHORT).show()

        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            filterList(filteredlist)
        }
    }


}