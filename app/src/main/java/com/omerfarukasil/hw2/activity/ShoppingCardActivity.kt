package com.omerfarukasil.hw2.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.databinding.ActivityShoppingCardBinding

class ShoppingCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityShoppingCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}