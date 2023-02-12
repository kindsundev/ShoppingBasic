package com.example.mvvmshopping.ui.dialog

import com.example.mvvmshopping.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)

}