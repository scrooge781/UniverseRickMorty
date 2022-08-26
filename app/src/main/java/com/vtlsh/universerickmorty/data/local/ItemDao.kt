package com.vtlsh.universerickmorty.data.local

import androidx.room.Dao
import androidx.room.Query
import com.vtlsh.universerickmorty.data.model.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getURMItemList(): List<Item>

}