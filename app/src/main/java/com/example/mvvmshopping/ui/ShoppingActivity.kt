package com.example.mvvmshopping.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshopping.R
import com.example.mvvmshopping.adapter.ShoppingItemAdapter
import com.example.mvvmshopping.data.db.ShoppingDatabase
import com.example.mvvmshopping.data.db.entities.ShoppingItem
import com.example.mvvmshopping.data.repositories.ShoppingRepository
import com.example.mvvmshopping.ui.dialog.AddDialogListener
import com.example.mvvmshopping.ui.dialog.AddShoppingItemDialog
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    private lateinit var viewModel : ShoppingViewModel
    private lateinit var adapter: ShoppingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        initViewModel()
        initAdapter()
        initListeners()
    }

    private fun initViewModel() {
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory)[ShoppingViewModel::class.java]
    }


    private fun initAdapter() {
        adapter = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems()
            .observe(this, Observer {
                adapter.items = it
                adapter.notifyDataSetChanged()
            })
    }

    private fun initListeners() {
        fab.setOnClickListener {
            AddShoppingItemDialog(this,
                object: AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}