/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import java.text.NumberFormat
import com.example.inventory.data.ItemsRepository

/**
 * `ItemEntryViewModel` adalah ViewModel yang bertanggung jawab untuk memvalidasi dan menyisipkan data item ke dalam database Room.
 * ViewModel ini mengelola logika aplikasi terkait dengan pengelolaan input UI item dan menyediakan fungsionalitas
 */
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    // Holds current item UI state
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Fungsi `updateUiState` digunakan untuk memperbarui status UI item (`itemUiState`) dengan data item yang baru.
     * Fungsi ini menerima `ItemDetails` sebagai parameter, memperbarui status, dan memvalidasi input baru yang diberikan.
     */
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /**
     * Fungsi `validateInput` memvalidasi input yang diberikan untuk memastikan bahwa semua nilai yang diperlukan (nama, harga, dan kuantitas) tidak kosong.
     */
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

    /**
     * Fungsi `saveItem` menyimpan data item ke dalam database jika input dianggap valid berdasarkan hasil validasi.
     */
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }
}

/**
 * `ItemUiState` adalah data class yang mewakili status UI dari suatu item.
 * Kelas ini terdiri dari detail item yang dimasukkan (`ItemDetails`) dan status validasi entri input (`isEntryValid`).
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/**
 * `ItemDetails` adalah data class yang menyimpan informasi mengenai detail suatu item seperti `id`, `name`, `price`, dan `quantity`.
 */
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 *  Jika `price` atau `quantity` tidak valid (bukan nilai numerik yang sesuai), nilai default 0.0 atau 0 akan digunakan.
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)


