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
// Kelas OfflineItemsRepository mengimplementasikan interface ItemsRepository
// Kelas `OfflineItemsRepository` bertanggung jawab untuk mengimplementasikan pengelolaan data secara offline dengan memanfaatkan Room DAO
import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository { // Mendefinisikan kelas dengan itemDao sebagai parameter konstruktor
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems() // Fungsi ini mengembalikan aliran (stream) semua data item yang ada dalam bentuk daftar.

    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id) // Fungsi ini mengembalikan aliran data dari item tertentu berdasarkan ID yang diberikan sebagai parameter

    override suspend fun insertItem(item: Item) = itemDao.insert(item) // Fungsi ini berfungsi untuk menyisipkan item baru ke dalam database. Operasi dilakukan secara asinkron dengan `suspend`.

    override suspend fun deleteItem(item: Item) = itemDao.delete(item) // Fungsi ini menghapus data item dari database. Operasi penghapusan dilakukan secara asinkron dengan `suspend`

    override suspend fun updateItem(item: Item) = itemDao.update(item) // Fungsi ini memperbarui data item yang ada di dalam database. Perubahan pada data akan disimpan dengan mengganti data lama
}