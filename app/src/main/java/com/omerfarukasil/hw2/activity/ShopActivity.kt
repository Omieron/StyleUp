package com.omerfarukasil.hw2.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.omerfarukasil.hw2.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}