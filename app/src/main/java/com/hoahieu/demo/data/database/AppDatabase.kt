package com.hoahieu.demo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hoahieu.demo.data.dao.CurrencyDao
import com.hoahieu.demo.data.model.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}

const val DEFAULT_DB_PATH = "database/demo.db"
const val DB_NAME = "demo.db"

fun createRoomDatabase(appContext: Context): AppDatabase =
    Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME)
        .createFromAsset(DEFAULT_DB_PATH)
        .build()