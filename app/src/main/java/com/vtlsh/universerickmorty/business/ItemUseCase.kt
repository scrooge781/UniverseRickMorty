package com.vtlsh.universerickmorty.business

import com.vtlsh.universerickmorty.data.local.ItemServiceImpl
import com.vtlsh.universerickmorty.data.model.Item
import javax.inject.Inject

class ItemUseCase @Inject constructor(
    private val itemServiceImpl: ItemServiceImpl
) {

    suspend fun getItemList(): List<Item> {
        return itemServiceImpl.getItemList()
    }

}