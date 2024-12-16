package com.omerfarukasil.hw2.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.omerfarukasil.hw2.util.ConstantsDB
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ConstantsDB.CLOTHESTABLE)
class Clothes (
    @PrimaryKey
    @SerializedName("id")
    var id : Int,
    @SerializedName("type")
    var type : String,
    @SerializedName("name")
    var name : String,
    @SerializedName("gender")
    var gender : String,
    @SerializedName("img")
    var img : String,
    @SerializedName("stock")
    var stock : Int,
    @SerializedName("size")
    var size : String
) : Parcelable