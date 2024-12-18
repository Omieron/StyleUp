package com.omerfarukasil.hw2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.db.Clothes

class ShoppingCardItemRecyclerViewAdapter(
    private val productList: List<Clothes>
) : RecyclerView.Adapter<ShoppingCardItemRecyclerViewAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImg: ImageView = itemView.findViewById(R.id.productImg)
        val productNameTxtView: TextView = itemView.findViewById(R.id.productNameTxtView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productList[position]

        holder.productNameTxtView.text = currentProduct.name

        val imgUrlAddress = currentProduct.img
        Glide.with(holder.itemView.context)
            .load(imgUrlAddress)
            .fitCenter()
            .into(holder.productImg)
    }

    override fun getItemCount(): Int = productList.size
}
