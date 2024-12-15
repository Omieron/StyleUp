package com.omerfarukasil.hw2.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omerfarukasil.hw2.db.Pants
import com.omerfarukasil.hw2.util.ConstantsDB

@Dao
interface PantsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPant(pants: Pants): Long

    @Update
    fun updatePant(pants: Pants): Int

    @Delete
    fun deletePant(pants: Pants): Int

    @Query("SELECT * FROM ${ConstantsDB.PANTSTABLE} WHERE id=:id")
    fun getPant(id: Int): Pants

    @Query("SELECT * FROM ${ConstantsDB.PANTSTABLE}")
    fun getAllPants(): List<Pants>

}