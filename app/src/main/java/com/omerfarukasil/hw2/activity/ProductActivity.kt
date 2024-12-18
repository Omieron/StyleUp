package com.omerfarukasil.hw2.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.adapter.ProductCustomRecyclerViewAdapter
import com.omerfarukasil.hw2.api.ApiClient
import com.omerfarukasil.hw2.api.service.ClothesService
import com.omerfarukasil.hw2.databinding.ActivityProductBinding
import com.omerfarukasil.hw2.databinding.ProductInfoLayoutBinding
import com.omerfarukasil.hw2.db.Clothes
import com.omerfarukasil.hw2.db.viewModal.UserViewModal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity(), ProductCustomRecyclerViewAdapter.ProductsAdapterInterface {

    private lateinit var binding: ActivityProductBinding
    private var clothesList: List<Clothes> = emptyList()  // Change to List<Clothes>
    private lateinit var productService: ClothesService
    private lateinit var productAdapter: ProductCustomRecyclerViewAdapter
    private lateinit var userViewModal: UserViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModal = ViewModelProvider(this).get(UserViewModal::class.java)

        // GET INTENT
        val gender = intent.getStringExtra("gender") ?: "Woman"
        val itemType = intent.getStringExtra("itemType") ?: "clothes"
        val userId = intent.getIntExtra("userId", 0)

        // ADJUST RECYCLERVIEW ADAPTER
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductCustomRecyclerViewAdapter(this, userId, this)
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

        binding.personIcon.setOnClickListener{
            if(userId != 0){
                val userObj = userViewModal.getUser(userId)
                createUserInfoDialog(userObj.toString())
            } else {
                Toast.makeText(this@ProductActivity, "First login or register", Toast.LENGTH_SHORT).show()
            }
        }

        // Card Icon
        binding.cardIcon.setOnClickListener{
            val intent = Intent(this@ProductActivity, ShoppingCardActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
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

    private fun createUserInfoDialog(str : String){
        val userInfoAlertDialog = AlertDialog.Builder(this@ProductActivity)

        userInfoAlertDialog.setTitle("User Info")
        userInfoAlertDialog.setIcon(R.drawable.baseline_person)
        userInfoAlertDialog.setMessage(str)

        userInfoAlertDialog.setPositiveButton("Back") { _, _ -> }

        userInfoAlertDialog.create().show()
    }


}
