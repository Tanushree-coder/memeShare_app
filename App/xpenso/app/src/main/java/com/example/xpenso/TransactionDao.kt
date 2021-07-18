package com.example.xpenso

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactionTable ORDER BY date DESC")
    fun loadAllTransactions(): LiveData<List<TransactionEntry?>?>?

    @Query("SELECT * FROM transactionTable WHERE id = :id")
    fun loadExpenseById(id: Int): LiveData<TransactionEntry?>?

    @Query("SELECT SUM(amount) FROM transactionTable WHERE transactionType =:transactionType")
    fun getAmountByTransactionType(transactionType: String?): Int

    @Query("SELECT SUM(amount) FROM transactionTable WHERE transactionType =:transactionType AND  date BETWEEN :startDate AND :endDate")
    fun getAmountbyCustomDates(transactionType: String?, startDate: Long, endDate: Long): Int

    @Query("SELECT SUM(amount) FROM transactionTable WHERE category=:category")
    fun getSumExpenseByCategory(category: String?): Int

    @Query("SELECT SUM(amount) FROM transactionTable WHERE category=:category AND date BETWEEN :startDate AND :endDate")
    fun getSumExpenseByCategoryCustomDate(category: String?, startDate: Long, endDate: Long): Int

    @get:Query("SELECT MIN(date) FROM transactionTable ")
    val firstDate: Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertExpense(transactionEntry: TransactionEntry?)

    @Delete
     fun removeExpense(transactionEntry: TransactionEntry?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
     fun updateExpenseDetails(transactionEntry: TransactionEntry?)
}