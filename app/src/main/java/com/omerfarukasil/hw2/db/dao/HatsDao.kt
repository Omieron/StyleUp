package com.omerfarukasil.hw2.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omerfarukasil.hw2.db.Hats
import com.omerfarukasil.hw2.util.ConstantsDB

@Dao
interface HatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHat(hats: Hats): Long

    @Update
    fun updateHat(hats: Hats): Int

    @Delete
    fun deleteHat(hats: Hats): Int

    @Query("SELECT * FROM ${ConstantsDB.HATSTABLE} WHERE id=:id")
    fun getHat(id: Int): Hats

    @Query("SELECT * FROM ${ConstantsDB.HATSTABLE}")
    fun getAllHats(): List<Hats>

}