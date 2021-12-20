package com.androiddevs.shoppinglisttestingyt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingItem
import com.androiddevs.shoppinglisttestingyt.data.remote.responses.ImageResponse
import com.androiddevs.shoppinglisttestingyt.others.Event
import com.androiddevs.shoppinglisttestingyt.others.Resource
import com.androiddevs.shoppinglisttestingyt.repositories.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.sql.StatementEvent

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingListRepository
): ViewModel () {

    val shoppingItems = repository.observeShoppingItem()
    val priceSum = repository.observePriceSum()

    private val _image= MutableLiveData<Event<Resource<ImageResponse>>>()
    val image : LiveData<Event<Resource<ImageResponse>>> = _image

    private val _currentImageUrl= MutableLiveData<String>()
    val currentImageUrl : LiveData<String> = _currentImageUrl

    private val _insertShoppingItemStatus= MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus : LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    fun setCurImage(url : String){
        _currentImageUrl.postValue(url)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) =viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name:String , amountString : String, priceString : String){

    }

    fun searchForImage(imageQuery : String){

    }













}