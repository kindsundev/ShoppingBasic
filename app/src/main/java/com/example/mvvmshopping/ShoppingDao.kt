package com.example.mvvmshopping

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    /*
    * setup between update and insert
    * when it doesn't have an id it will add it, otherwise it will update
    * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrInsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}
