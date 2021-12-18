package com.androiddevs.shoppinglisttestingyt.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.temporal.TemporalAmount


@Entity(tableName = "shopping_item")
class ShoppingItem(
    var name : String,
    var amount: Int,
    var price : Float,
    var imageUrl  :String,
    @PrimaryKey(autoGenerate = true)
    var id: Int?= null
) {
}