package com.omerfarukasil.hw2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omerfarukasil.hw2.db.dao.ClothesDao
import com.omerfarukasil.hw2.db.dao.ShoppingCardDao
import com.omerfarukasil.hw2.db.dao.UserDao
import com.omerfarukasil.hw2.util.ConstantsDB

@Database(entities = [User::class, ShoppingCard::class, Clothes::class], version = 4)
abstract class AppDatabase : RoomDatabase(){
    abstract fun UserDao(): UserDao
    abstract fun ClothesDao(): ClothesDao
    abstract fun ShoppingCardDao(): ShoppingCardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase?=null

        fun getDatabase(context: Context):AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    ConstantsDB.DATABASENAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}