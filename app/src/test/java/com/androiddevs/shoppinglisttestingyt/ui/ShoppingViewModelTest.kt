package com.androiddevs.shoppinglisttestingyt.ui

import com.androiddevs.shoppinglisttestingyt.getOrAwaitValueTest
import com.androiddevs.shoppinglisttestingyt.others.Constants
import com.androiddevs.shoppinglisttestingyt.others.Status
import com.androiddevs.shoppinglisttestingyt.repositories.FakeShoppingRepository
import com.google.common.truth.Truth.assertThat
import org.bouncycastle.asn1.x500.style.RFC4519Style.name
import org.junit.Before
import org.junit.Test

class ShoppingViewModelTest {

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert Shopping item with empty field, returns error`() {
        viewModel.insertShoppingItem("name", "", "10.30")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert Shopping item with too long name, returns error`() {

            val string = buildString {
                for (i in 1..Constants.MAX_NAME_LENGTH + 1){
                    append(1)
                }
            }
            viewModel.insertShoppingItem(string,"5","1.02")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
}























