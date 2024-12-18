package com.omerfarukasil.hw2.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omerfarukasil.hw2.db.ShoppingCard
import com.omerfarukasil.hw2.util.ConstantsDB

@Dao
interface ShoppingCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemInShoppingCard(shoppingCard: ShoppingCard): Long

    @Update
    fun updateItemInShoppingCard(shoppingCard: ShoppingCard): Int

    @Delete
    fun deleteItemInShoppingCard(shoppingCard: ShoppingCard): Int

    @Query("SELECT productId FROM ${ConstantsDB.SHOPPINGCARDTABLE} WHERE userId = :userId")
    fun getUserAllShopping(userId : Int): List<Int>

}