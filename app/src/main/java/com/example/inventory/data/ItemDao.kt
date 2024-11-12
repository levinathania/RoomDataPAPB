package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * @Dao adalah anotasi yang menandakan interface ini sebagai Data Access Object (DAO),
 * yaitu tempat di mana kita mendefinisikan berbagai operasi database yang dapat dilakukan
 * terhadap entitas `Item`. DAO ini digunakan oleh Room untuk menghasilkan implementasi kode
 * yang menjalankan query database secara efisien dan aman.
 */
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)
    //Fungsi `insert` digunakan untuk memasukkan objek `Item` baru ke dalam tabel.
    @Update
    suspend fun update(item: Item)
    //Fungsi `update` digunakan untuk memperbarui data `Item` yang sudah ada di tabel.
    @Delete
    suspend fun delete(item: Item)
    // Fungsi `delete` digunakan untuk menghapus objek `Item` dari tabel.
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>
    //Fungsi `getItem` mengambil satu entri dari tabel berdasarkan `id` yang diberikan.
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
    //Fungsi `getAllItems` mengambil semua entri dari tabel `items` dan mengurutkannya berdasarkan nama.
}