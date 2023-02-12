package com.example.mvvmshopping.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshopping.adapter.ShoppingItemAdapter
import com.example.mvvmshopping.data.db.ShoppingDatabase
import com.example.mvvmshopping.data.db.entities.ShoppingItem
import com.example.mvvmshopping.data.repositories.ShoppingRepository
import com.example.mvvmshopping.databinding.ActivityShoppingBinding
import com.example.mvvmshopping.ui.dialog.AddDialogListener
import com.example.mvvmshopping.ui.dialog.AddShoppingItemDialog

class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding
    private lateinit var viewModel : ShoppingViewModel
    private lateinit var adapter: ShoppingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initAdapter()
        initListeners()
    }

    private fun initViewModel() {
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]
    }


    private fun initAdapter() {
        adapter = ShoppingItemAdapter(listOf(), viewModel)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems()
            .observe(this, Observer {
                adapter.items = it
                adapter.notifyDataSetChanged()
            })
    }

    private fun initListeners() {
        binding.fab.setOnClickListener {
            AddShoppingItemDialog(this,
                object: AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}