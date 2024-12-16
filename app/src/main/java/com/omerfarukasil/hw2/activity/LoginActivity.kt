package com.omerfarukasil.hw2.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.omerfarukasil.hw2.databinding.ActivityLoginBinding
import com.omerfarukasil.hw2.db.viewModal.UserViewModal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userViewModal: UserViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        userViewModal = ViewModelProvider(this).get(UserViewModal::class.java)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailView.text.toString()
            val password = binding.passwordView.text.toString()

            userViewModal.checkUserCredential(email, password).observe(this) { user ->
                if (user != null) {
                    val intent = Intent(this@LoginActivity, ShopActivity::class.java)
                    intent.putExtra("userId", user.id)
                    intent.putExtra("gender", user.gender)
                    startActivity(intent)
                    binding.warningTitle.visibility = View.INVISIBLE
                    finish()
                } else {
                    lifecycleScope.launch {
                        binding.warningTitle.visibility = View.VISIBLE
                        delay(1500)
                        binding.warningTitle.visibility = View.INVISIBLE
                    }
                }
            }
        }

        binding.cancelBtn.setOnClickListener{
            finish()
        }

    }
}