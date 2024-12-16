package com.omerfarukasil.hw2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.db.Clothes

class ProductCustomRecyclerViewAdapter(
    private val context: Context,
) : RecyclerView.Adapter<ProductCustomRecyclerViewAdapter.ClothesViewHolder>() {

    // Interface
    interface ProductsAdapterInterface {
        fun displayProduct(clothes: Clothes)
        fun displayProducts(clothes: MutableList<Clothes>)
    }

    companion object {
        var selectedPosition = RecyclerView.NO_POSITION

        fun setSelectedPosition() {
            selectedPosition = RecyclerView.NO_POSITION
        }
    }
    private var recyclerItemValues = emptyList<Clothes>()
    private var adapterInterface: ProductsAdapterInterface = context as ProductsAdapterInterface

    fun setData(items:List<Clothes>){
        recyclerItemValues = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = recyclerItemValues.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return ClothesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        val currentProduct = recyclerItemValues[position]

        // Set selected or not
        holder.itemView.isSelected = (position == selectedPosition)

        // Change background based on selection
        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.item_layout_selected_background)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.item_layout_default_background)
        }

        // Bind product data to view
        holder.clothesName.text = currentProduct.name
        val imgUrlAddress = currentProduct.img
        Glide.with(context)
            .load(imgUrlAddress)
            .fitCenter()
            .into(holder.clothesImg)

        // Set click listener for selection and notify the interface
        holder.itemView.setOnClickListener {
            // Update selected position
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            // Notify item changes
            notifyItemChanged(previousPosition) // Deselect previous
            notifyItemChanged(selectedPosition) // Select new

            // Notify the interface about the selected product
            adapterInterface.displayProduct(currentProduct)
        }
    }

    inner class ClothesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clothesName: TextView = itemView.findViewById(R.id.productNameTxtView) // Assuming "productNameTxtView" is correct ID for name
        val clothesImg: ImageView = itemView.findViewById(R.id.productImg) // Assuming "productImg" is correct ID for image
    }
}
