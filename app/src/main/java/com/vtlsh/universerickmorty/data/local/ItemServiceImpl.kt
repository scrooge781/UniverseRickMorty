package com.vtlsh.universerickmorty.data.local

import com.vtlsh.universerickmorty.data.model.Item
import javax.inject.Inject

class ItemServiceImpl @Inject constructor(
    private val itemDao: ItemDao
) : ItemService {

    override suspend fun getItemList(): List<Item> {
        return itemDao.getURMItemList()
    }

}