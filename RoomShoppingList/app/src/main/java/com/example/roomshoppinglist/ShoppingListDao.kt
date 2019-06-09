package com.example.roomshoppinglist

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

// Contains methods that can be used on database, Query is SQL command
@Dao
interface ShoppingListDao {

    @Query("Select * from shopping_list_table")
    fun getAll(): MutableList<ShoppingListItem>

    @Insert // Insert item to table, return auto increment id (Long) to the caller application
    fun insert(item: ShoppingListItem): Long

    @Query("DELETE FROM shopping_list_table WHERE id = :itemId")
    fun delete(itemId: Int)
}