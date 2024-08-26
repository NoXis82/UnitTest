package com.noxis.unittest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.google.android.material.textview.MaterialTextView
import com.noxis.unittest.R
import com.noxis.unittest.data.local.ShoppingItem
import javax.inject.Inject

class ShoppingAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<ShoppingItem>() {
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var shoppingItems: List<ShoppingItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_shopping,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = shoppingItems.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val shoppingItem = shoppingItems[position]
        holder.itemView.apply {
            glide.load(shoppingItem.imageUrl).into(holder.itemView.findViewById(R.id.ivShoppingImage))
            holder.itemView.findViewById<MaterialTextView>(R.id.tvName).text = shoppingItem.name
            val amountText = "${shoppingItem.amount}x"
            holder.itemView.findViewById<MaterialTextView>(R.id.tvShoppingItemAmount).text = amountText
            val priceText = "${shoppingItem.price}€"
            holder.itemView.findViewById<MaterialTextView>(R.id.tvShoppingItemPrice).text = priceText
        }
    }

}