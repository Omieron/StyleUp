package com.omerfarukasil.hw2.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omerfarukasil.hw2.db.User
import com.omerfarukasil.hw2.util.ConstantsDB

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : User): Long

    @Update
    fun updateUser(user : User): Int

    @Delete
    fun deleteUser(user : User): Int

    @Query("SELECT * FROM ${ConstantsDB.USERTABLE} WHERE id =:id")
    fun getUser(id : Int): User

}