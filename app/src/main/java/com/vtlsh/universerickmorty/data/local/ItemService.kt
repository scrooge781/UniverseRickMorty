package com.vtlsh.universerickmorty.data.local

import com.vtlsh.universerickmorty.data.model.Item

interface ItemService {

    suspend fun getItemList(): List<Item>
}