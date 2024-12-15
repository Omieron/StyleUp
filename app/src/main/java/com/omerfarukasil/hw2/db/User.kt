package com.omerfarukasil.hw2.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.omerfarukasil.hw2.util.ConstantsDB

@Entity(tableName = ConstantsDB.USERTABLE)
class User (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 1,
    var name : String,
    var gender : Char,
    var headSize : String,
    var topSize : String,
    var pantSize : Int,
    var shoeSize : Double
)