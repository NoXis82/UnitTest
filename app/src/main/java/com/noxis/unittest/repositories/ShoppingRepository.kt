package com.noxis.unittest.repositories

import androidx.lifecycle.LiveData
import com.noxis.unittest.data.local.ShoppingItem
import com.noxis.unittest.data.remote.model.ImageResponse
import com.noxis.unittest.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}