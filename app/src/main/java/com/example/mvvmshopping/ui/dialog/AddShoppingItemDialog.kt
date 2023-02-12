package com.example.mvvmshopping.ui.dialog

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.mvvmshopping.R
import com.example.mvvmshopping.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(
    context: Context,
    val addDialogListener: AddDialogListener
) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)
        initListeners()
    }

    private fun initListeners() {
        onClickAdd()
        onClickCancel()
    }

    private fun onClickAdd() {
        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if (name.isEmpty() or amount.isEmpty()) {
                Toast.makeText(context, "Please enter all the information!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
    }

    private fun onClickCancel() {
        tvCancel.setOnClickListener {
            cancel()
        }
    }
}
