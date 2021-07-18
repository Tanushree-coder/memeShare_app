package com.example.xpenso

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenso.Constants.editExpenseString
import com.example.xpenso.Constants.editIncomeString
import com.example.xpenso.Constants.incomeCategory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CustomAdapter(var context: Context, transactionEntries: ArrayList<TransactionEntry>?) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var transactionEntries= ArrayList<TransactionEntry>()
    private var appDatabase: AppDatabase? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val amount: String
        holder.categoryTextViewrv.text=(transactionEntries[position].category)
        if ((transactionEntries[position].transactionType)==incomeCategory)
         {
            amount = "+" + transactionEntries[position].amount
            holder.amountTextViewrv.text = amount
            holder.amountTextViewrv.setTextColor(Color.parseColor("#aeea00"))
        } else {
            amount = "-" + transactionEntries[position].amount
            holder.amountTextViewrv.text = amount
            holder.amountTextViewrv.setTextColor(Color.parseColor("#ff5722"))
        }
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

        val dateToBeSet = sdf.parse(transactionEntries[position].date.toString())

        holder.dateTextViewrv.text = dateToBeSet.toString()
        holder.descriptionTextViewrv.text = transactionEntries[position].description
    }

    override fun getItemCount(): Int {
        return if (transactionEntries.size == 0) {
            0
        } else {
            transactionEntries.size
        }
    }

    fun getTransactionEntries(): List<TransactionEntry> {
        return transactionEntries
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryTextViewrv: TextView = itemView.findViewById(R.id.categoryTextViewrv)
        var amountTextViewrv: TextView = itemView.findViewById(R.id.amountTextViewrv)
        var descriptionTextViewrv: TextView = itemView.findViewById(R.id.descriptionTextViewrv)
        var dateTextViewrv: TextView = itemView.findViewById(R.id.dateTextViewrv)

        init {
            appDatabase = AppDatabase.getInstance(context)
            itemView.setOnClickListener {
                val intent = Intent(context, AddExpenseActivity::class.java)
                val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
                val date = sdf.format(transactionEntries[adapterPosition].date)
                if (transactionEntries[adapterPosition].transactionType == incomeCategory ) {
                    intent.putExtra("from",editIncomeString)
                    intent.putExtra("amount", transactionEntries[adapterPosition].amount)
                    intent.putExtra(
                        "description",
                        transactionEntries[adapterPosition].description
                    )
                    intent.putExtra("date", date)
                    intent.putExtra("id", transactionEntries[adapterPosition].id)
                } else {
                    intent.putExtra("from",editExpenseString)
                    intent.putExtra("amount", transactionEntries[adapterPosition].amount)
                    intent.putExtra(
                        "description",
                        transactionEntries[adapterPosition].description
                    )
                    intent.putExtra("date", date)
                    intent.putExtra("category", transactionEntries[adapterPosition].category)
                    intent.putExtra("id", transactionEntries[adapterPosition].id)
                }


               context.startActivity(intent)
            }
        }
    }

    init {
        if (transactionEntries != null) {
            this.transactionEntries = transactionEntries
        }
    }
}