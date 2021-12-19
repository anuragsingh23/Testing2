package com.androiddevs.shoppinglisttestingyt.repositories

import androidx.lifecycle.LiveData
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingDao
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingItem
import com.androiddevs.shoppinglisttestingyt.data.remote.PixaBayApi
import com.androiddevs.shoppinglisttestingyt.data.remote.responses.ImageResponse
import com.androiddevs.shoppinglisttestingyt.others.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShopppintListRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixaBayApi: PixaBayApi
) : ShoppingListRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)

    }

    override  fun observeShoppingItem(): LiveData<List<ShoppingItem>> {
       return shoppingDao.observeAllShoppingItem()
    }

    override  fun observePriceSum(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String) : Resource<ImageResponse> {

       return try {

            val response = pixaBayApi.searchForImage(imageQuery)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("an unknown error", null)

            } else {
                Resource.error("an unknown error", null)
            }

        } catch (e: Exception) {

            Resource.error("an unknown error", null)
        }
    }
}