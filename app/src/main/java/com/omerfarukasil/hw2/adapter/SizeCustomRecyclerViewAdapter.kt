package com.omerfarukasil.hw2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omerfarukasil.hw2.R

class SizeCustomRecyclerViewAdapter (
    private val context : Context,
    private val sizes : MutableList<Int>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Interface
    interface SizeAdapterInterface {
        fun displaySize(size : Int)
        fun displaySizes(sizes : MutableList<Int>)
    }

    companion object {
        var selectedPosition = RecyclerView.NO_POSITION
        // I don't want to import RecyclerView in a different class so create a method here
        fun setSelectedPosition() {
            selectedPosition = RecyclerView.NO_POSITION
        }
    }

    private var adapterInterface : SizeAdapterInterface = context as SizeAdapterInterface

    override fun getItemCount(): Int = sizes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_size_layout, parent, false)
        return SizeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentProduct = sizes[position]

        // Set selected or not
        holder.itemView.isSelected = (position == ProductCustomRecyclerViewAdapter.selectedPosition)

        // Change background based on selection
        if (position == ProductCustomRecyclerViewAdapter.selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.item_layout_selected_background)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.item_layout_default_background)
        }

        // Set click listener
        holder.itemView.setOnClickListener {
            // Update selected position
            val previousPosition = ProductCustomRecyclerViewAdapter.selectedPosition
            ProductCustomRecyclerViewAdapter.selectedPosition = holder.adapterPosition

            // Notify changes
            notifyItemChanged(previousPosition) // Deselect previous
            notifyItemChanged(ProductCustomRecyclerViewAdapter.selectedPosition) // Select new

            // Notify interface
            adapterInterface.displaySize(currentProduct)
        }
    }

    inner class SizeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sizeName: TextView = itemView.findViewById(R.id.sizeTextView)
    }
}