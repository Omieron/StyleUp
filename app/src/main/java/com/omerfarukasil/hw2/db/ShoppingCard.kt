package com.omerfarukasil.hw2.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.omerfarukasil.hw2.util.ConstantsDB
import kotlinx.parcelize.Parcelize


@Entity(
    tableName = ConstantsDB.SHOPPINGCARDTABLE,
    foreignKeys = [
        ForeignKey(
            entity = User::class, // Bağlanacağı tablo
            parentColumns = ["id"], // User tablosundaki primary key
            childColumns = ["userId"], // ShoppingCard'daki foreign key
            onDelete = ForeignKey.CASCADE // Kullanıcı silinirse, bağlı ShoppingCard kayıtları da silinir
        )
    ]
)
@Parcelize
class ShoppingCard (
    @PrimaryKey
    var userId : Int,
    var productId : Int,
    var productSize : String
) : Parcelable