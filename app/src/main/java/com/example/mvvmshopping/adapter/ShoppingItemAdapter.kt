package com.example.mvvmshopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmshopping.R
import com.example.mvvmshopping.data.db.entities.ShoppingItem
import com.example.mvvmshopping.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]
        holder.itemView.tvName.text = currentItem.name
        holder.itemView.tvAmount.text = currentItem.amount.toString()

        holder.itemView.ivDelete.setOnClickListener { viewModel.delete(currentItem) }

        holder.itemView.ivPlus.setOnClickListener {
            currentItem.amount++
            viewModel.upsert(currentItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if (currentItem.amount > 0) {
                currentItem.amount--
                viewModel.upsert(currentItem)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}