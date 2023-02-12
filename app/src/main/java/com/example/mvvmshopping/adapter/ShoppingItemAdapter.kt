package com.example.mvvmshopping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmshopping.R
import com.example.mvvmshopping.data.db.entities.ShoppingItem
import com.example.mvvmshopping.databinding.ShoppingItemBinding
import com.example.mvvmshopping.ui.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShoppingItemBinding.inflate(layoutInflater, parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = items.size


    inner class ShoppingViewHolder(
        private val binding: ShoppingItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShoppingItem) {
            binding.tvName.text = item.name
            binding.tvAmount.text = item.amount.toString()

            binding.ivDelete.setOnClickListener { viewModel.delete(item) }

            binding.ivPlus.setOnClickListener {
                item.amount++
                viewModel.upsert(item)
            }

            binding.ivMinus.setOnClickListener {
                if (item.amount > 0) {
                    item.amount--
                    viewModel.upsert(item)
                }
            }
        }
    }
}