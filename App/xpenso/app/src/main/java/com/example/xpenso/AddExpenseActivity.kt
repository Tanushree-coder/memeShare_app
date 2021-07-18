package com.example.xpenso

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.xpenso.Constants.addExpenseString
import com.example.xpenso.Constants.addIncomeString
import com.example.xpenso.Constants.editExpenseString
import com.example.xpenso.Constants.editIncomeString
import com.example.xpenso.Constants.expenseCategory
import com.example.xpenso.Constants.incomeCategory
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


abstract class AddExpenseActivity : AppCompatActivity() {
    abstract var appDatabase: AppDatabase
    var amountTextInputEditText: TextInputEditText? = null
    var descriptionTextInputEditText: TextInputEditText? = null
    var dateTextView: TextView? = null
    var dateLinearLayout: LinearLayout? = null
    var categorySpinner: Spinner? = null
    var categories: ArrayList<String?>? = null
    var myCalendar: Calendar? = null
    var description: String? = null
    var dateOfExpense: Date? = null
    private val datePickerDialog: DatePickerDialog? = null


    var amount = 0
    var categoryOfExpense: String? =
        null

    var categoryOfTransaction: String? = null
    var intentFrom: String? = null
    var transactionViewModel: TransactionViewModel? = null
    var transactionid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        appDatabase = AppDatabase.getInstance(this@AddExpenseActivity)
        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel::class.java)
        categories = ArrayList()
        myCalendar = Calendar.getInstance()
        setDateToTextView()

        val intent = intent
        intentFrom = intent.getStringExtra("from")
        if (intentFrom == addIncomeString) {
            categoryOfTransaction = incomeCategory
            title = "Add Income"
            categories!!.add("Income")
            categorySpinner!!.isClickable = false
            categorySpinner!!.isEnabled = false
            categorySpinner!!.adapter = ArrayAdapter(
                this@AddExpenseActivity, android.R.layout.simple_list_item_1,
                categories!!
            )
        } else if (intentFrom == addExpenseString) {
            categoryOfTransaction = expenseCategory
            title = "Add Expense"
            categories!!.add("Food")
            categories!!.add("Travel")
            categories!!.add("Clothes")
            categories!!.add("Movies")
            categories!!.add("Health")
            categories!!.add("Grocery")
            categories!!.add("Other")
            categorySpinner?.adapter = ArrayAdapter(
                this@AddExpenseActivity,
                android.R.layout.simple_list_item_1, categories!!
            )
        } else if (intentFrom == editIncomeString) {
            title = "Edit Income"
            amountTextInputEditText?.setText(intent.getIntExtra("amount", 0).toString())
            amountTextInputEditText?.setSelection(amountTextInputEditText!!.getText()!!.length)
            descriptionTextInputEditText?.setText(intent.getStringExtra("description"))
            descriptionTextInputEditText?.setSelection(descriptionTextInputEditText!!.getText()!!.length)
            transactionid = intent.getIntExtra("id", -1)
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            try {
                val date = sdf.parse(intent.getStringExtra("date"))
                myCalendar?.time = date
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            dateTextView?.setText(intent.getStringExtra("date"))
            categoryOfTransaction = incomeCategory
            categories!!.add("Income")
            categorySpinner?.isClickable = false
            categorySpinner?.isEnabled = false
            categorySpinner?.adapter = ArrayAdapter(
                this@AddExpenseActivity, android.R.layout.simple_list_item_1,
                categories!!
            )
        } else if (intentFrom == editExpenseString) {
            categoryOfTransaction = expenseCategory
            title = "Edit Expense"
            amountTextInputEditText?.setText(intent.getIntExtra("amount", 0).toString())
            amountTextInputEditText?.setSelection(amountTextInputEditText!!.getText()!!.length)
            descriptionTextInputEditText?.setText(intent.getStringExtra("description"))
            descriptionTextInputEditText?.setSelection(descriptionTextInputEditText!!.getText()!!.length)
            dateTextView?.setText(intent.getStringExtra("date"))
            transactionid = intent.getIntExtra("id", -1)
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            try {
                val date = sdf.parse(intent.getStringExtra("date"))
                myCalendar?.setTime(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            categories!!.add("Food")
            categories!!.add("Travel")
            categories!!.add("Clothes")
            categories!!.add("Movies")
            categories!!.add("Health")
            categories!!.add("Grocery")
            categories!!.add("Other")
            categorySpinner?.adapter = ArrayAdapter(
                this@AddExpenseActivity, android.R.layout.simple_list_item_1,
                categories!!
            )
            categorySpinner?.setSelection(categories!!.indexOf(intent.getStringExtra("category")))
        }
        dateLinearLayout?.setOnClickListener(View.OnClickListener { showDatePicker() })
    }

    fun showDatePicker() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                myCalendar!![Calendar.YEAR] = year
                myCalendar!![Calendar.MONTH] = month
                myCalendar!![Calendar.DAY_OF_MONTH] = dayOfMonth
                setDateToTextView()
            }
        val datePickerDialog = DatePickerDialog(
            this@AddExpenseActivity, dateSetListener,
            myCalendar!![Calendar.YEAR],
            myCalendar!![Calendar.MONTH], myCalendar!![Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()

    }

    private fun setDateToTextView() {
        val date = myCalendar!!.time
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        val dateToBeSet = sdf.format(date)
        dateTextView!!.text = dateToBeSet
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_expense_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.saveButton ->
                if (amountTextInputEditText!!.text.toString().isEmpty()
                    || descriptionTextInputEditText!!.text.toString().isEmpty()
                ) {
                    if (amountTextInputEditText!!.text.toString()
                            .isEmpty()
                    ) amountTextInputEditText!!.error =
                        "Amount cannot be empty"
                    if (descriptionTextInputEditText!!.text.toString()
                            .isEmpty()
                    ) descriptionTextInputEditText!!.error =
                        "Please write some description"
                } else {
                    amount = amountTextInputEditText!!.text.toString().toInt()
                    description = descriptionTextInputEditText!!.text.toString()
                    dateOfExpense = myCalendar!!.time
                    categoryOfExpense =
                        if (intentFrom == addIncomeString || intentFrom == editIncomeString) "Income" else categories!![categorySpinner!!.selectedItemPosition]


                    val mTransactionEntry = TransactionEntry(
                        amount,
                        categoryOfExpense!!,
                        description!!,
                        dateOfExpense!!,
                        categoryOfTransaction!!
                    )

//
//    companion object {
//        private var appDatabase: AppDatabase? = null
//        private val LOG_TAG = AddExpenseActivity::class.java.simpleName
//    }

                }
        }
        return true
    }
}

