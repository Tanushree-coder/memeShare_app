package com.example.xpenso

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.xpenso.MainActivity.Companion.fab
import com.github.mikephil.charting.charts.PieChart


class BalanceFragment : Fragment(), OnItemSelectedListener {
    private var mAppDb: AppDatabase? = null
    var pieChart: PieChart? = null
    var spinner: Spinner? = null
    private var balanceTv: TextView? = null
    private var incomeTv: TextView? = null
    private var expenseTv: TextView? = null
    private var dateTv: TextView? = null
    private var balanceAmount = 0
    private var incomeAmount = 0
    private var expenseAmount = 0
    private var foodExpense = 0
    private var travelExpense = 0
    private var clothesExpense = 0
    private var moviesExpense = 0
    private var heathExpense = 0
    private var groceryExpense = 0
    private var otherExpense = 0
    var firstDate: Long = 0
    var expenseList: ArrayList<ExpenseList>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_balance, container, false)
        pieChart = view.findViewById(R.id.balancePieChart)
        spinner = view.findViewById(R.id.spinner)
        spinner?.onItemSelectedListener = this
        //mAppDb = context?.let { AppDatabase.getInstance(this@BalanceFragment) }
        balanceTv = view.findViewById(R.id.totalAmountTextView)
        expenseTv = view.findViewById(R.id.amountForExpenseTextView)
        incomeTv = view.findViewById(R.id.amountForIncomeTextView)
        dateTv = view.findViewById(R.id.dateTextView)
        expenseList = ArrayList<ExpenseList>()
//        allBalanceAmount
//        setupPieChart()
        return view

        //TODO 1.Change constraint to linear and change entire layout
        //TODO 2.Align piechart properly with label
        //TODO 3.See if can opytimize queries and spinner state and read about fragment lifecycle
    }

    private fun setupSpinner() {
        val arrayAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.date_array,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = arrayAdapter
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.i("fragment", isVisibleToUser.toString())
        if (isVisibleToUser) {
            setupSpinner()
            fab?.setVisibility(View.GONE)
        } else {
            fab?.setVisibility(View.VISIBLE)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

//    private fun setupPieChart() {
//        AppExecutors.getInstance().diskIO().execute(Runnable {
//            if (spinner!!.selectedItemPosition == 0) allPieValues else if (spinner!!.selectedItemPosition == 1) {
//                try {
//                    weekPieValues
//                } catch (e: ParseException) {
//                    e.printStackTrace()
//                }
//            } else if (spinner!!.selectedItemPosition == 2) {
//                try {
//                    monthPieValues
//                } catch (e: ParseException) {
//                    e.printStackTrace()
//                }
//            }
//            expenseList!!.clear()
//            if (foodExpense != 0) expenseList!!.add(ExpenseList("Food", foodExpense))
//            if (travelExpense != 0) expenseList!!.add(ExpenseList("Travel", travelExpense))
//            if (clothesExpense != 0) expenseList!!.add(ExpenseList("Clothes", clothesExpense))
//            if (moviesExpense != 0) expenseList!!.add(ExpenseList("Movies", moviesExpense))
//            if (heathExpense != 0) expenseList!!.add(ExpenseList("Health", heathExpense))
//            if (groceryExpense != 0) expenseList!!.add(ExpenseList("Grocery", groceryExpense))
//            if (otherExpense != 0) expenseList!!.add(ExpenseList("Other", otherExpense))
//        })
//        AppExecutors.getInstance().mainThread().execute(Runnable {
//            val pieEntries: MutableList<PieEntry> = ArrayList<PieEntry>()
//            for (i in expenseList!!.indices) {
//                pieEntries.add(
//                    PieEntry(
//                        expenseList!![i].getAmount(),
//                        expenseList!![i].getCategory()
//                    )
//                )
//            }
//            pieChart?.setVisibility(View.VISIBLE)
//            val dataSet = PieDataSet(pieEntries, null)
//            dataSet.setColors(ColorTemplate.COLORFUL_COLORS)
//            val pieData = PieData(dataSet)
//            pieData.setValueTextSize(16F)
//            pieData.setValueTextColor(Color.WHITE)
//            pieData.setValueFormatter(PercentFormatter())
//            pieChart?.setUsePercentValues(true)
//            pieChart?.setData(pieData)
//            pieChart?.animateY(1000)
//            pieChart?.invalidate()
//            pieChart?.getDescription()?.setText("")
//            var l: Legend = pieChart?.getLegend() ?:
//            //l.setPosition(Legend.LegendPosition.LEFT_OF_CHART)
//            //l.setXEntrySpace(8f);
//            //l.setYEntrySpace(1f);
//            //l.setYOffset(0f);
//        })
//    }

//    override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
//        if (adapterView.selectedItemPosition == 0) {
//            allBalanceAmount
//            setupPieChart()
//        } else if (adapterView.selectedItemPosition == 1) {
//            //This week
//            try {
//                weekBalanceAmount
//                setupPieChart()
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//        } else if (adapterView.selectedItemPosition == 2) {
//            //This month
//            try {
//                monthBalanceAmount
//                setupPieChart()
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//        }
//    }

//    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
//    private val allPieValues: Unit
//        get() {
//            foodExpense = mAppDb.transactionDao().getSumExpenseByCategory("Food")
//            travelExpense = mAppDb.transactionDao().getSumExpenseByCategory("Travel")
//            clothesExpense = mAppDb.transactionDao().getSumExpenseByCategory("Clothes")
//            moviesExpense = mAppDb.transactionDao().getSumExpenseByCategory("Movies")
//            heathExpense = mAppDb.transactionDao().getSumExpenseByCategory("Health")
//            groceryExpense = mAppDb.transactionDao().getSumExpenseByCategory("Grocery")
//            otherExpense = mAppDb.transactionDao().getSumExpenseByCategory("Other")
//        }
//
//    // Set the calendar to sunday of the current week
//    @get:Throws(ParseException::class)
//    private val weekPieValues: Unit
//        get() {
//            val calendar: Calendar
//            calendar = Calendar.getInstance()
//            val df: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            var startDate: String? = ""
//            var endDate: String? = ""
//            // Set the calendar to sunday of the current week
//            calendar[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
//            startDate = df.format(calendar.time)
//            val sDate: Date? = df.parse(startDate)
//            val sdate: Long = (sDate?.time ?: calendar.add(Calendar.DATE, 6)) as Long
//            endDate = df.format(calendar.time)
//            val eDate: Date? = df.parse(endDate)
//            val edate = eDate?.time
//            foodExpense =
//                mAppDb?.transactionDao().getSumExpenseByCategoryCustomDate("Food", sdate, edate)
//            travelExpense =
//                mAppDb?.transactionDao().getSumExpenseByCategoryCustomDate("Travel", sdate, edate)
//            clothesExpense =
//                mAppDb?.transactionDao().getSumExpenseByCategoryCustomDate("Clothes", sdate, edate)
//            moviesExpense =
//                mAppDb?.transactionDao().getSumExpenseByCategoryCustomDate("Movies", sdate, edate)
//            heathExpense =
//                mAppDb?.transactionDao().getSumExpenseByCategoryCustomDate("Health", sdate, edate)
//            groceryExpense =
//                mAppDb?.transactionDao().getSumExpenseByCategoryCustomDate("Grocery", sdate, edate)
//            otherExpense =
//                mAppDb?.transactionDao().getSumExpenseByCategoryCustomDate("Other", sdate, edate)
//        }
//
//    @get:Throws(ParseException::class)
//    private val monthPieValues: Unit
//        private get() {
//            val calendar: Calendar
//            calendar = Calendar.getInstance()
//            val df: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            var startDate: String? = ""
//            var endDate: String? = ""
//            calendar[Calendar.DAY_OF_MONTH] = 1
//            startDate = df.format(calendar.time)
//            val sDate: Date? = df.parse(startDate)
//            val sdate = sDate?.time
//            calendar[Calendar.DAY_OF_MONTH] = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//            endDate = df.format(calendar.time)
//            val eDate:Date? = df.parse(endDate)
//            val edate = eDate?.time
//            foodExpense =
//                mAppDb.transactionDao().getSumExpenseByCategoryCustomDate("Food", sdate, edate)
//            travelExpense =
//                mAppDb.transactionDao().getSumExpenseByCategoryCustomDate("Travel", sdate, edate)
//            clothesExpense =
//                mAppDb.transactionDao().getSumExpenseByCategoryCustomDate("Clothes", sdate, edate)
//            moviesExpense =
//                mAppDb.transactionDao().getSumExpenseByCategoryCustomDate("Movies", sdate, edate)
//            heathExpense =
//                mAppDb.transactionDao().getSumExpenseByCategoryCustomDate("Health", sdate, edate)
//            groceryExpense =
//                mAppDb.transactionDao().getSumExpenseByCategoryCustomDate("Grocery", sdate, edate)
//            otherExpense =
//                mAppDb.transactionDao().getSumExpenseByCategoryCustomDate("Other", sdate, edate)
//        }
//
//    //get date when first transaction date and todays date
//    private val allBalanceAmount: Unit
//        private get() {
//}