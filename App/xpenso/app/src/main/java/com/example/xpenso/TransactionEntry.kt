package com.example.xpenso

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "transactionTable")
class TransactionEntry {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var amount: Int
    var category: String
    var description: String
    var date // COMPLETED: 13-09-2018 Add appropriate type converter
            : Date
    var transactionType //to decide whether income or expense
            : String

    @Ignore
    constructor(
        amount: Int,
        category: String,
        description: String,
        date: Date,
        transactionType: String
    ) {
        this.amount = amount
        this.category = category
        this.description = description
        this.date = date
        this.transactionType = transactionType
    }

    constructor(
        id: Int,
        amount: Int,
        category: String,
        description: String,
        date: Date,
        transactionType: String
    ) {
        this.id = id
        this.amount = amount
        this.category = category
        this.description = description
        this.date = date
        this.transactionType = transactionType
    }




}