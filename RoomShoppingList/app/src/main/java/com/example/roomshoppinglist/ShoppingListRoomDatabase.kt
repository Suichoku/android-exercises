package com.example.roomshoppinglist

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/** This file contains the database holder and serves as the main access point for
 *  the underlying connection to your app's persisted, relational data */
@Database(entities = [ShoppingListItem::class], version = 1)
abstract class ShoppingListRoomDatabase : RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao
}