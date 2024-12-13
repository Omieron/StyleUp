package com.omerfarukasil.hw2.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.databinding.ActivityEntranceBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EntranceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEntranceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.womanLayout.setOnClickListener {
            binding.manLayout.isEnabled = false
            lifecycleScope.launch {
                binding.womanLayout.setBackgroundResource(R.drawable.pressed_sex_layout)
                delay(500)
                binding.womanLayout.setBackgroundResource(0)
                binding.manLayout.isEnabled = true
            }
        }

        binding.manLayout.setOnClickListener {
            binding.womanLayout.isEnabled = false
            lifecycleScope.launch {
                binding.manLayout.setBackgroundResource(R.drawable.pressed_sex_layout)
                delay(500)
                binding.manLayout.setBackgroundResource(0)
                binding.womanLayout.isEnabled = true
            }
        }
    }
}