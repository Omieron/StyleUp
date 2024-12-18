package com.omerfarukasil.hw2.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.adapter.ProductCustomRecyclerViewAdapter
import com.omerfarukasil.hw2.adapter.ShoppingCardItemRecyclerViewAdapter
import com.omerfarukasil.hw2.databinding.ActivityShoppingCardBinding
import com.omerfarukasil.hw2.db.viewModal.ShoppingCardModal
import com.omerfarukasil.hw2.db.viewModal.UserViewModal

class ShoppingCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCardBinding
    private lateinit var productAdapter: ShoppingCardItemRecyclerViewAdapter
    private lateinit var shoppingCardViewModal: ShoppingCardModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityShoppingCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getIntExtra("userId", 0)

        shoppingCardViewModal = ViewModelProvider(this).get(ShoppingCardModal::class.java)

        val cardItem = shoppingCardViewModal.getUserAllShoppingData(userId)

        //productAdapter = ShoppingCardItemRecyclerViewAdapter(cardItem)

    }
}