package com.hoahieu.demo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hoahieu.demo.data.model.CurrencyEntity

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getAll(): List<CurrencyEntity>

    @Insert
    fun insertAll(vararg entities: CurrencyEntity)
}