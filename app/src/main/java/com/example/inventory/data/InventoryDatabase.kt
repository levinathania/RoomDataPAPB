package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * kelas abstrak yang memperluas `RoomDatabase` untuk menyediakan implementasi database Room.
 * Kelas ini mendefinisikan database untuk menyimpan entitas `Item` dan menyediakan akses ke DAO (Data Access Object) melalui fungsi `itemDao()`
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null //`Instance` adalah variabel `InventoryDatabase?` yang bertindak sebagai cache untuk menyimpan instance tunggal database.
        //`@Volatile` memastikan bahwa perubahan pada `Instance` segera terlihat di semua thread.
        fun getDatabase(context: Context): InventoryDatabase { //Fungsi `getDatabase` memberikan instance `InventoryDatabase`.
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}