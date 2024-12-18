package com.omerfarukasil.hw2.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.omerfarukasil.hw2.util.ConstantsDB
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = ConstantsDB.SHOPPINGCARDTABLE)
class ShoppingCard (
    @PrimaryKey
    var userId : Int,
    var productId : Int,
    var productSize : String
) : Parcelable