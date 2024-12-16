package com.omerfarukasil.hw2.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omerfarukasil.hw2.db.Clothes
import com.omerfarukasil.hw2.util.ConstantsDB

@Dao
interface ClothesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClothes(clothes: Clothes): Long

    @Update
    fun updateClothes(clothes: Clothes): Int

    @Delete
    fun deleteClothes(clothes: Clothes): Int

    @Query("SELECT * FROM ${ConstantsDB.CLOTHESTABLE} WHERE type = :type")
    fun getClothesByType(type:String): List<Clothes>

    @Query("SELECT * FROM ${ConstantsDB.CLOTHESTABLE} WHERE id=:id")
    fun getClothes(id: Int): Clothes

    @Query("SELECT * FROM ${ConstantsDB.CLOTHESTABLE}")
    fun getAllClothes(): List<Clothes>

}