package com.omerfarukasil.hw2.db

import androidx.room.Entity
import com.omerfarukasil.hw2.util.ConstantsDB

@Entity(tableName = ConstantsDB.SHOESTABLE)
class Shoes (
    var id : Int,
    var name : String,
    var gender : Char,
    var img : String,
    var stock : Int,
    var size : IntArray
)