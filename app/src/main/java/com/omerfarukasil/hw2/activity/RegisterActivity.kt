package com.omerfarukasil.hw2.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.databinding.ActivityRegisterBinding
import com.omerfarukasil.hw2.db.User
import com.omerfarukasil.hw2.db.viewModal.UserViewModal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userViewModal: UserViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        userViewModal = ViewModelProvider(this).get(UserViewModal::class.java)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener{
            val name = binding.nameView.text.toString()
            val email = binding.emailView.text.toString()
            val password = binding.passwordView.text.toString()
            val gender : String = if(binding.womanBtn.isChecked)
                "Woman"
            else
                "Man"
            if(userViewModal.checkUserEmail(email))
                lifecycleScope.launch {
                    binding.warningTitle.visibility = View.VISIBLE
                    delay(1500)
                    binding.warningTitle.visibility = View.INVISIBLE
                }
            else {
                val userObj = User(0, name,email,password,gender)
                userViewModal.addUser(userObj)
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                binding.warningTitle.visibility = View.INVISIBLE
                finish()
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }


    }
}