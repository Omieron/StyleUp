package com.omerfarukasil.hw2.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.adapter.ProductCustomRecyclerViewAdapter
import com.omerfarukasil.hw2.api.ApiClient
import com.omerfarukasil.hw2.api.service.ClothesService
import com.omerfarukasil.hw2.databinding.ActivityProductBinding
import com.omerfarukasil.hw2.db.Clothes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity(), ProductCustomRecyclerViewAdapter.ProductsAdapterInterface {

    private lateinit var binding: ActivityProductBinding
    private var clothesList: List<Clothes> = emptyList()  // Change to List<Clothes>
    private lateinit var productService: ClothesService
    private lateinit var productAdapter: ProductCustomRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // GET INTENT
        val gender = intent.getStringExtra("gender") ?: "Woman"
        val itemType = intent.getStringExtra("itemType") ?: "clothes"

        // ADJUST RECYCLERVIEW ADAPTER
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductCustomRecyclerViewAdapter(this)
        binding.itemRecyclerView.adapter = productAdapter

        // GET JSON PARAMs
        productService = ApiClient.getClient().create(ClothesService::class.java)
        val request = findAndCallJson(itemType, gender)

        request.enqueue(object : Callback<List<Clothes>> {
            override fun onFailure(call: Call<List<Clothes>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("JSONARRAYPARSE", "Error: " + t.message.toString())
            }

            override fun onResponse(call: Call<List<Clothes>>, response: Response<List<Clothes>>) {
                Log.d("JSONARRAYPARSE", "Response taken")
                if (response.isSuccessful) {
                    clothesList = response.body() ?: emptyList() // Assign List<Clothes> to clothesList
                    Log.d("JSONARRAYPARSE", "Clothes taken from server: ${clothesList.size} items")
                    productAdapter.setData(clothesList) // Send List<Clothes> to the adapter
                }
            }
        })
    }

    // Choose JSON request based on item type and gender
    private fun findAndCallJson(itemType: String, gender: String): Call<List<Clothes>> {
        return if (gender == "Woman") {
            when (itemType) {
                "clothes" -> productService.getWomanClothes()
                "pants" -> productService.getWomanPants()
                "shoes" -> productService.getWomanShoes()
                else -> productService.getWomanHats()
            }
        } else { // Man
            when (itemType) {
                "clothes" -> productService.getManClothes()
                "pants" -> productService.getManPants()
                "shoes" -> productService.getManShoes()
                else -> productService.getManHats()
            }
        }
    }

    // Implement the interface methods
    override fun displayProduct(clothes: Clothes) {
        // Handle the product display logic, e.g., show the product details in another view or activity
        Toast.makeText(this, "Selected product: ${clothes.name}", Toast.LENGTH_SHORT).show()
    }

    override fun displayProducts(clothes: MutableList<Clothes>) {
        // Optional: Handle logic to display a list of clothes (if needed)
    }
}
