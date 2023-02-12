package com.example.mvvmshopping.ui.dialog

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.mvvmshopping.data.db.entities.ShoppingItem
import com.example.mvvmshopping.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(
    context: Context,
    val addDialogListener: AddDialogListener
) : AppCompatDialog(context) {

    private lateinit var binding: DialogAddShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        onClickAdd()
        onClickCancel()
    }

    private fun onClickAdd() {
        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString()

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
        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}
