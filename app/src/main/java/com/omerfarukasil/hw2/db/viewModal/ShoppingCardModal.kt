package com.omerfarukasil.hw2.db.viewModal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.omerfarukasil.hw2.db.AppDatabase
import com.omerfarukasil.hw2.db.ShoppingCard
import com.omerfarukasil.hw2.db.dao.ShoppingCardDao
import com.omerfarukasil.hw2.db.dao.UserDao

class ShoppingCardModal(application: Application) :  AndroidViewModel(application) {

    var shoppingCardDao: ShoppingCardDao = AppDatabase.getDatabase(application).ShoppingCardDao()

    fun insertShoppingCard(shoppingCard: ShoppingCard){
        shoppingCardDao.insertItemInShoppingCard(shoppingCard)
    }

    fun getUserAllShoppingData(userId: Int) : List<Int> {
        return shoppingCardDao.getUserAllShopping(userId)
    }

}