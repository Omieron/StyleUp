package com.omerfarukasil.hw2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omerfarukasil.hw2.db.dao.ClothesDao
import com.omerfarukasil.hw2.db.dao.HatsDao
import com.omerfarukasil.hw2.db.dao.PantsDao
import com.omerfarukasil.hw2.db.dao.ShoesDao
import com.omerfarukasil.hw2.db.dao.ShoppingCardDao
import com.omerfarukasil.hw2.db.dao.UserCredentialDao
import com.omerfarukasil.hw2.db.dao.UserDao

@Database(entities = [User::class, UserCredential::class, ShoppingCard::class,
                    Clothes::class, Pants::class, Shoes::class, Hats::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun UserDao(): UserDao
    abstract fun UserCredentialDao(): UserCredentialDao
    abstract fun ClothesDao(): ClothesDao
    abstract fun HatsDao(): HatsDao
    abstract fun ShoesDao(): ShoesDao
    abstract fun PantsDao(): PantsDao
    abstract fun ShoppingCardDao(): ShoppingCardDao

}