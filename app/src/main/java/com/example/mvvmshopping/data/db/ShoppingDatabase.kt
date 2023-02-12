package com.example.mvvmshopping.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmshopping.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        /*
        * make this same singleton pattern
        * key volatile: save this in main memory
        * (all thread know about the change when certain thread change)
        * */
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}