package com.vtlsh.universerickmorty.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity
data class Item(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String

) : Parcelable {
    constructor(name: String): this(0, name)
}