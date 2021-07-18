package com.example.xpenso

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TransactionEntry::class],version = 1,exportSchema = false)
@TypeConverters(*[DateConverter::class])
         abstract class AppDatabase : RoomDatabase() {

    private val LOG_TAG: String? = AppDatabase::class.simpleName
    private val LOCK: Any = Object()
    private val DATABASE_NAME: String = "TransactionDb"

    companion object {
        @Volatile
        private var sInstance: AppDatabase?=null

        fun getInstance(context: Context): AppDatabase {


            return sInstance?: synchronized(this){
                val instance=Room.databaseBuilder(context,AppDatabase::class.java,"DATABASE_NAME").build()
                sInstance=instance
                instance
            }

        }

    }

     abstract fun transactionDao():TransactionDao
}
