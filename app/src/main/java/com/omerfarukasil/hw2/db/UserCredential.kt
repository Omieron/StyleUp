package com.omerfarukasil.hw2.db

import androidx.room.Entity
import com.omerfarukasil.hw2.util.ConstantsDB

@Entity(tableName = ConstantsDB.USERCREDENTIALTABLE)
class UserCredential(
    var id : Int,
    var email : String,
    var password : String
)