package com.omerfarukasil.hw2.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omerfarukasil.hw2.db.UserCredential
import com.omerfarukasil.hw2.util.ConstantsDB

@Dao
interface UserCredentialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserCredential(userCredential: UserCredential): Long

    @Update
    fun updateUserCredential(userCredential: UserCredential): Int

    @Delete
    fun deleteUserCredential(userCredential: UserCredential): Int

    @Query("SELECT id FROM ${ConstantsDB.USERCREDENTIALTABLE} WHERE email = :email AND password = :password")
    fun checkUserCredential(email : String, password: String): Int

}