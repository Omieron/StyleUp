package com.omerfarukasil.hw2.db

import androidx.room.Entity
import com.omerfarukasil.hw2.util.ConstantsDB

@Entity(tableName = ConstantsDB.PANTSTABLE)
class Pants (
    var id : Int,
    var name : String,
    var img : String,
    var stock : Int,
    var size : IntArray
)