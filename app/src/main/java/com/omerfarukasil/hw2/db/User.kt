package com.omerfarukasil.hw2.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.omerfarukasil.hw2.util.ConstantsDB
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ConstantsDB.USERTABLE)
class User (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var name : String,
    var email : String,
    var password : String,
    var gender : String
) : Parcelable {

    override fun toString(): String {
        val gen : String = if(gender == "W") "Woman" else "Man"
        return "Id : $id\n" +
                "Name : $name\n" +
                "Email : $email\n" +
                "Gender : $gen\n"
    }
}