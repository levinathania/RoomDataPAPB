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
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.inventory.data.ItemsRepository

/**
 * `ItemEditViewModel` adalah `ViewModel` yang bertugas untuk mengambil dan memperbarui data item
 *  dari `ItemsRepository`. Dengan menggunakan `ViewModel`, data aplikasi yang dikelola tetap terjaga
 *  saat terjadi perubahan konfigurasi seperti rotasi layar.
 */
class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /**
     * `itemUiState` adalah variabel yang menyimpan status saat ini dari UI item yang sedang
     *  diedit, seperti data item yang sedang dimodifikasi. Variabel ini menggunakan `mutableStateOf`
     *  agar setiap perubahan data dapat dilacak oleh Compose dan otomatis memperbarui tampilan.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set
    // `itemId` adalah ID dari item yang sedang diedit, yang diperoleh dari `savedStateHandle`.
    //  ID ini digunakan untuk mengakses data item tertentu dari `ItemsRepository`.
    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean { // Fungsi `validateInput` digunakan untuk memeriksa apakah input yang dimasukkan pengguna
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}
