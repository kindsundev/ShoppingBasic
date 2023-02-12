package com.example.mvvmshopping.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.mvvmshopping.data.db.entities.ShoppingItem
import com.example.mvvmshopping.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

   /*
   *  normally for database operations, i use i/o option
   *  but room basically provides main safety
   *  so i can launch it in main context
   * */

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}