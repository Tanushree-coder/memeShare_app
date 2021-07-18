package com.example.xpenso

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    val expenseList: LiveData<List<TransactionEntry?>?>?
    private val appDatabase: AppDatabase

    //    The code below can be ignored
    fun updateTransaction(transactionEntry: TransactionEntry?) {
        updateTransactionDetails(appDatabase).execute(transactionEntry)
    }

    private class updateTransactionDetails(appDatabase: AppDatabase) :
        AsyncTask<TransactionEntry?, Void?, Void?>() {
        private val mdb: AppDatabase

         override fun doInBackground(vararg transactionEntries : TransactionEntry?): Void? {
            mdb.transactionDao().updateExpenseDetails(transactionEntries[0])
            return null
        }

        init {
            mdb = appDatabase
        }

    }

    init {
        appDatabase = AppDatabase.getInstance(getApplication())
        expenseList = appDatabase.transactionDao().loadAllTransactions()
    }
}