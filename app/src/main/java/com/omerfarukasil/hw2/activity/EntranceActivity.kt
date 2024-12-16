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
            val intent = Intent(this@EntranceActivity, ShopActivity::class.java)
            intent.putExtra("gender", "Woman")
            intent.putExtra("userId", 0)

            // Background life cycle
            binding.manLayout.isEnabled = false
            lifecycleScope.launch {
                binding.womanLayout.setBackgroundResource(R.drawable.pressed_sex_layout)
                delay(500)
                binding.womanLayout.setBackgroundResource(0)
                binding.manLayout.isEnabled = true
            }

            startActivity(intent)
        }

        binding.manLayout.setOnClickListener {
            val intent = Intent(this@EntranceActivity, ShopActivity::class.java)
            intent.putExtra("gender", "Man")
            intent.putExtra("userId", 0)

            // Background life cycle
            binding.womanLayout.isEnabled = false
            lifecycleScope.launch {
                binding.manLayout.setBackgroundResource(R.drawable.pressed_sex_layout)
                delay(500)
                binding.manLayout.setBackgroundResource(0)
                binding.womanLayout.isEnabled = true
            }

            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val intent = Intent(this@EntranceActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerBtn.setOnClickListener {
            val intent = Intent(this@EntranceActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}