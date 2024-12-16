package com.omerfarukasil.hw2.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.databinding.ActivityShopBinding
import com.omerfarukasil.hw2.db.viewModal.UserViewModal

class ShopActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopBinding
    private lateinit var userViewModal: UserViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        userViewModal = ViewModelProvider(this).get(UserViewModal::class.java)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getIntExtra("userId", 0)
        val userGender = intent.getStringExtra("gender")


        // Person Icon
        binding.personIcon.setOnClickListener{
            if(userId != 0){
                val userObj = userViewModal.getUser(userId)
                createUserInfoDialog(userObj.toString())
            } else {
                Toast.makeText(this@ShopActivity, "First login or register", Toast.LENGTH_SHORT).show()
            }
        }

        // Card Icon
        binding.cardIcon.setOnClickListener{
            val intent = Intent(this@ShopActivity, ShoppingCardActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        // Clothes Layout
        binding.clothesLayout.setOnClickListener {
            val intent = Intent(this@ShopActivity, ProductActivity::class.java)
            intent.putExtra("gender", userGender)
            intent.putExtra("itemType", "clothes")
            startActivity(intent)
        }

        // Pants Layout
        binding.pantsLayout.setOnClickListener {
            val intent = Intent(this@ShopActivity, ProductActivity::class.java)
            intent.putExtra("gender", userGender)
            intent.putExtra("itemType", "pants") //clothes ise type = 2
            startActivity(intent)
        }

        // Hats Layout
        binding.hatLayout.setOnClickListener {
            val intent = Intent(this@ShopActivity, ProductActivity::class.java)
            intent.putExtra("gender", userGender)
            intent.putExtra("itemType", "hats") //clothes ise type = 3
            startActivity(intent)
        }

        // Shoes Layout
        binding.shoesLayout.setOnClickListener {
            val intent = Intent(this@ShopActivity, ProductActivity::class.java)
            intent.putExtra("gender", userGender)
            intent.putExtra("itemType", "shoes") //clothes ise type = 4
            startActivity(intent)
        }

        // Purchase Button
        binding.purchaseBtn.setOnClickListener{
            val intent = Intent(this@ShopActivity, ShoppingCardActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }

    private fun createUserInfoDialog(str : String){
        val userInfoAlertDialog = AlertDialog.Builder(this@ShopActivity)

        userInfoAlertDialog.setTitle("User Info")
        userInfoAlertDialog.setIcon(R.drawable.baseline_person)
        userInfoAlertDialog.setMessage(str)

        userInfoAlertDialog.setPositiveButton("Back") { _, _ -> }

        userInfoAlertDialog.create().show()
    }


}