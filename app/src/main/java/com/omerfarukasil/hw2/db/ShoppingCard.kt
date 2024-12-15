package com.omerfarukasil.hw2.db

import androidx.room.Entity
import com.omerfarukasil.hw2.util.ConstantsDB

@Entity(tableName = ConstantsDB.SHOPPINGCARDTABLE)
class ShoppingCard (
    var userId : Int,
    var productId : Int,
    var productSize : String
)