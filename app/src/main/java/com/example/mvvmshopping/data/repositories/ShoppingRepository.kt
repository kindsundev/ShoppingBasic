package com.example.mvvmshopping.data.repositories

import com.example.mvvmshopping.data.db.ShoppingDatabase
import com.example.mvvmshopping.data.db.entities.ShoppingItem

class ShoppingRepository (private val db: ShoppingDatabase) {

    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().updateOrInsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}