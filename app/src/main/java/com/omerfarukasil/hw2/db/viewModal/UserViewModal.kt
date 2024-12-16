package com.omerfarukasil.hw2.db.viewModal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.omerfarukasil.hw2.db.AppDatabase
import com.omerfarukasil.hw2.db.User
import com.omerfarukasil.hw2.db.dao.UserDao
import kotlinx.coroutines.launch

class UserViewModal(application: Application) :  AndroidViewModel(application){

    var userDao: UserDao = AppDatabase.getDatabase(application).UserDao()

    fun checkUserCredential(email: String, password: String): LiveData<User?> {
        return userDao.checkUserCredential(email, password)
    }

    fun addUser(user : User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    fun getUser(id : Int) : User {
        return userDao.getUser(id)
    }

    fun checkUserEmail(email : String) : Boolean {
        val result = userDao.checkUserEmail(email)
        return result > 0
    }

}