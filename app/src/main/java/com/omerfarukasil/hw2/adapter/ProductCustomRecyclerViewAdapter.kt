package com.omerfarukasil.hw2.adapter

import SizeCustomRecyclerViewAdapter
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.db.Clothes

// For httpClientOk
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.omerfarukasil.hw2.databinding.ProductInfoLayoutBinding


class ProductCustomRecyclerViewAdapter(
    private val context: Context
) : RecyclerView.Adapter<ProductCustomRecyclerViewAdapter.ClothesViewHolder>() {

    // Interface
    interface ProductsAdapterInterface {
        fun displayProduct(clothes: Clothes)
        fun displayProducts(clothes: MutableList<Clothes>)
    }

    interface OnClothesClickListener{
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

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // Bağlantı zaman aşımı
            .readTimeout(30, TimeUnit.SECONDS)    // Okuma zaman aşımı
            .writeTimeout(30, TimeUnit.SECONDS)   // Yazma zaman aşımı
            .build()
        Glide.get(context).registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(okHttpClient)
        )

        holder.clothesName.text = currentProduct.name
        val imgUrlAddress = currentProduct.img
        Glide.with(context)
            .load(imgUrlAddress)
            .fitCenter()
            .into(holder.clothesImg)

        holder.itemView.setOnClickListener {
            // Update selected position
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            // Notify item changes
            notifyItemChanged(previousPosition) // Deselect previous
            notifyItemChanged(selectedPosition) // Select new

            showItemCustomDialog(currentProduct)
        }
    }

    private fun showItemCustomDialog(clothes: Clothes) {
        // Binding kullanarak layoutu inflate et
        val dialogBinding = ProductInfoLayoutBinding.inflate(LayoutInflater.from(context))

        // Dialog oluştur
        val dialog = Dialog(context).apply {
            setContentView(dialogBinding.root)
        }

        val sizeList: List<String> = clothes.size.split(",").map { it.trim() }

        // Bedenler RecyclerView'ı başlat
        val sizeAdapter = SizeCustomRecyclerViewAdapter(sizeList) // Assume sizes is List<String>
        dialogBinding.productSizeRecyclerView.apply {
            adapter = sizeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        // Dialog içerisindeki görseller ve veriler
        dialogBinding.productNameTxtView.text = clothes.name
        dialogBinding.productStockView.text = "Stock: ${clothes.stock}"

        // Glide ile ürün resmi
        Glide.with(context)
            .load(clothes.img)
            .fitCenter()
            .into(dialogBinding.productInfoImgView)

        // Dialog kapatma butonu
        dialogBinding.productInfoBackBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


    inner class ClothesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clothesName: TextView = itemView.findViewById(R.id.productNameTxtView) // Assuming "productNameTxtView" is correct ID for name
        val clothesImg: ImageView = itemView.findViewById(R.id.productImg) // Assuming "productImg" is correct ID for image
    }
}



