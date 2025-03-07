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

package com.example.inventory.data

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
import kotlinx.coroutines.flow.Flow

/**
 * `ItemsRepository` adalah interface yang mendefinisikan operasi dasar untuk mengelola entitas `Item`
 */
interface ItemsRepository {

    /**
     *
     * Fungsi `getAllItemsStream` digunakan untuk mengambil seluruh daftar item dari sumber data,
     * dan mengembalikannya dalam bentuk `Flow` dari daftar `Item`.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Fungsi `getItemStream` digunakan untuk mengambil satu item dari sumber data berdasarkan `id` yang diberikan.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Fungsi `insertItem` digunakan untuk menambahkan data item baru ke dalam sumber data.
     */
    suspend fun insertItem(item: Item)


    /**
     * Fungsi `deleteItem` digunakan untuk menghapus data item tertentu dari sumber data.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Fungsi `updateItem` digunakan untuk memperbarui data item yang ada di sumber data.
     */
    suspend fun updateItem(item: Item)
}
