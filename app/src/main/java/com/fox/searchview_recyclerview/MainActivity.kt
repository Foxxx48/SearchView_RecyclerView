package com.fox.searchview_recyclerview

import android.R
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fox.searchview_recyclerview.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

//    lateinit var catsRecyclerView: RecyclerView
    lateinit var catsAdapter: CatListAdapter
    lateinit var catsList: ArrayList<Cat>
//    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buildRecyclerView()

        binding.idSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                // on below line we are checking
                // if query exist or not.

                catsAdapter.filter(query)

            return true
        }

                override fun onQueryTextChange(newText: String): Boolean {
            // if query text is change in that case we
            // are filtering our adapter with
            // new text on below line.
            catsAdapter.filter(newText)
            return false
        }
    })

    }
    private fun buildRecyclerView() {

        // below line we are creating a new array list
        catsList = ArrayList<Cat>()

        // below line is to add data to our array list.
        catsList.add(Cat("Barsik", 1))
        catsList.add(Cat("Murzik", 2))
        catsList.add(Cat("Stesha", 3))
        catsList.add(Cat("Vaska", 4))
        catsList.add(Cat("Barsik", 1))
        catsList.add(Cat("Murzik", 2))
        catsList.add(Cat("Stesha", 3))
        catsList.add(Cat("Vaska", 4))


        // initializing our adapter class.
        catsAdapter = CatListAdapter(catsList)

        // adding layout manager to our recycler view.
        val manager = LinearLayoutManager(this)
        binding.idRVCats.setHasFixedSize(true)

        // setting layout manager
        // to our recycler view.
        binding.idRVCats.setLayoutManager(manager)

        // setting adapter to
        // our recycler view.
        binding.idRVCats.setAdapter(catsAdapter)
    }


}