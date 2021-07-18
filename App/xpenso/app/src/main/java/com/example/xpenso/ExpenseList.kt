package com.example.xpenso

class ExpenseList(private var category:String, private var amount:Int)
{

    fun ExpenseList(category:String, amount:Int) {
        this.category = category
        this.amount = amount
    }

    fun getCategory(): String {
        return category
    }

    fun setCategory( category:String) {
        this.category = category
    }

    fun getAmount():Int {
        return amount
    }

    fun setAmount(amount:Int) {
        this.amount = amount
    }
}