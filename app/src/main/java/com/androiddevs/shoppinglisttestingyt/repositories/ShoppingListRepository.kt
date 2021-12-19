package com.androiddevs.shoppinglisttestingyt.repositories

import androidx.lifecycle.LiveData
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingItem
import com.androiddevs.shoppinglisttestingyt.data.remote.responses.ImageResponse
import com.androiddevs.shoppinglisttestingyt.others.Resource
import retrofit2.Response

interface ShoppingListRepository {


    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

     fun observeShoppingItem()  : LiveData<List<ShoppingItem>>

     fun observePriceSum() : LiveData<Float>

    suspend fun searchForImage(imageQuery: String) : Resource<ImageResponse>

}