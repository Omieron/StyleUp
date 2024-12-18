package com.omerfarukasil.hw2.adapter

import SizeCustomRecyclerViewAdapter
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.activity.ProductActivity
import com.omerfarukasil.hw2.databinding.ProductInfoLayoutBinding
import com.omerfarukasil.hw2.db.ShoppingCard
import com.omerfarukasil.hw2.db.viewModal.ShoppingCardModal
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.omerfarukasil.hw2.db.Clothes

class ProductCustomRecyclerViewAdapter(
    private val context: Context,
    private val userId: Int,
    private val app: ProductActivity
) : RecyclerView.Adapter<ProductCustomRecyclerViewAdapter.ClothesViewHolder>() {

    // Interface
    interface ProductsAdapterInterface {
        fun displayProduct(clothes: Clothes)
    }

    interface OnClothesClickListener {
        fun onProductClick(clothes: Clothes, context: Context)
    }

    companion object {
        var selectedPosition = RecyclerView.NO_POSITION

        fun setSelectedPosition() {
            selectedPosition = RecyclerView.NO_POSITION
        }
    }

    private var recyclerItemValues = emptyList<Clothes>()
    private var adapterInterface: ProductsAdapterInterface = context as ProductsAdapterInterface

    private val localAddedItems = mutableListOf<Int>()

    fun setData(items: List<Clothes>) {
        recyclerItemValues = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = recyclerItemValues.size

    override fun getItemViewType(position: Int): Int {
        return if (localAddedItems.contains(position)) {
            // Eğer ürün sepetteyse farklı layout tipi
            1
        } else {
            // Eğer ürün sepette değilse varsayılan layout tipi
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = if (viewType == 1) {

            layoutInflater.inflate(R.layout.product_layout_with_size, parent, false)
        } else {

            layoutInflater.inflate(R.layout.product_layout, parent, false)
        }
        return ClothesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        val currentProduct = recyclerItemValues[position]

        // Set selected or not
        holder.itemView.isSelected = (position == selectedPosition)

        // Set product name and image
        holder.clothesName.text = currentProduct.name
        val imgUrlAddress = currentProduct.img
        Glide.with(context)
            .load(imgUrlAddress)
            .fitCenter()
            .into(holder.clothesImg)

        // Add button click listener
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            // Notify item changes
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            showItemCustomDialog(currentProduct)
        }

        // Sepete eklenmişse butonun görünümünü değiştirme
//        if (localAddedItems.contains(position)) {
//            holder.itemView.setBackgroundResource(R.l)
//        }
    }

    private fun showItemCustomDialog(clothes: Clothes) {
        val dialogBinding = ProductInfoLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = Dialog(context).apply {
            setContentView(dialogBinding.root)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        val sizeList: List<String> = clothes.size.split(",").map { it.trim() }
        val sizeAdapter = SizeCustomRecyclerViewAdapter(sizeList)
        dialogBinding.productSizeRecyclerView.apply {
            adapter = sizeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        dialogBinding.productNameTxtView.text = clothes.name
        dialogBinding.productStockView.text = "Stock: ${clothes.stock}"

        Glide.with(context)
            .load(clothes.img)
            .fitCenter()
            .into(dialogBinding.productInfoImgView)

        val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                enlargeImageInDialog(clothes.img, dialog)
                return super.onDoubleTap(e)
            }
        })

        dialogBinding.productInfoImgView.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        dialogBinding.productInfoBackBtn.setOnClickListener {
            dialog.dismiss()
            SizeCustomRecyclerViewAdapter.setSelectedPosition()
        }

        dialogBinding.productAddBtn.setOnClickListener {
            val shoppingCard = ShoppingCard(userId, clothes.id, clothes.size)
            val shoppingCardViewModal = ViewModelProvider(app).get(ShoppingCardModal::class.java)
            shoppingCardViewModal.insertShoppingCard(shoppingCard)
            dialog.dismiss()

            // Mark the product as added to the cart locally by its position
            localAddedItems.add(selectedPosition)
            notifyItemChanged(selectedPosition)

            SizeCustomRecyclerViewAdapter.setSelectedPosition()
        }

        dialog.show()
    }

    private fun enlargeImageInDialog(imageUrl: String, parentDialog: Dialog) {
        val inflater = LayoutInflater.from(context)
        val enlargeView = inflater.inflate(R.layout.big_img_layout, null)

        val imageView: ImageView = enlargeView.findViewById(R.id.largeImageView)
        Glide.with(context)
            .load(imageUrl)
            .into(imageView)

        parentDialog.setContentView(enlargeView)

        enlargeView.findViewById<Button>(R.id.closeEnlargedImageBtn).setOnClickListener {
            parentDialog.dismiss()
        }
    }

    inner class ClothesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clothesName: TextView = itemView.findViewById(R.id.productNameTxtView)
        val clothesImg: ImageView = itemView.findViewById(R.id.productImg)
    }
}
