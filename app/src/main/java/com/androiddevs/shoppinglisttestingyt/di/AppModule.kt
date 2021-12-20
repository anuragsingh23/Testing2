package com.androiddevs.shoppinglisttestingyt.di

import android.content.Context
import androidx.room.Room
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingDao
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingItemDb
import com.androiddevs.shoppinglisttestingyt.data.remote.PixaBayApi
import com.androiddevs.shoppinglisttestingyt.others.Constants.BASE_URL
import com.androiddevs.shoppinglisttestingyt.others.Constants.DATABASE_NAME
import com.androiddevs.shoppinglisttestingyt.repositories.DefaultShopppintListRepository
import com.androiddevs.shoppinglisttestingyt.repositories.ShoppingListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
     fun provideShoppingItemDb(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(context,ShoppingItemDb::class.java,DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixaBayApi
    )=DefaultShopppintListRepository(dao,api) as ShoppingListRepository

    @Singleton
    @Provides
        fun provideShoppingDao(
        database  : ShoppingItemDb
    )  =database.shoppingDao()

    @Singleton
    @Provides
     fun providePixaBayApi() : PixaBayApi{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixaBayApi::class.java)
    }

}

