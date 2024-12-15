package com.omerfarukasil.hw2.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omerfarukasil.hw2.db.Shoes
import com.omerfarukasil.hw2.util.ConstantsDB

@Dao
interface ShoesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoes: Shoes): Long

    @Update
    fun updateShoe(shoes: Shoes): Int

    @Delete
    fun deleteShoe(shoes: Shoes): Int

    @Query("SELECT * FROM ${ConstantsDB.SHOESTABLE} WHERE id=:id")
    fun getShoe(id: Int): Shoes

    @Query("SELECT * FROM ${ConstantsDB.SHOESTABLE}")
    fun getAllShoes(): List<Shoes>

}