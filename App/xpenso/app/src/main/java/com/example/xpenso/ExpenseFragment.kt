package com.example.xpenso

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExpenseFragment : Fragment() {
    private var rv: RecyclerView? = null
    private var transactionEntries: ArrayList<TransactionEntry>? = null
    private var customAdapter: CustomAdapter? = null
    private var transactionViewModel: TransactionViewModel? = null
    private var mAppDb: AppDatabase? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    @NonNull
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_expense, container, false)
        rv = view.findViewById(R.id.transactionRecyclerView) as RecyclerView

        transactionEntries = ArrayList<TransactionEntry>()
        linearLayoutManager = LinearLayoutManager(activity)
        rv!!.layoutManager = linearLayoutManager
        rv!!.layoutManager=LinearLayoutManager(activity)
        setupViewModel()
        return view
    }

    private fun setupViewModel() {
        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel::class.java)
        transactionViewModel!!.expenseList?.observe(this,
            Observer<ArrayList<TransactionEntry>?> { transactionEntriesFromDb ->
                Log.i(LOG_TAG, "Actively retrieving from DB")
                transactionEntries = transactionEntriesFromDb

                for (i in transactionEntries!!.indices) {
                    val description: String = transactionEntries!![i].description
                    val amount: Int = transactionEntries!![i].amount
                    //Log.i("DESCRIPTION AMOUNT",description + String.valueOf(amount));
                }

                //                        Setting Adapter
                customAdapter = CustomAdapter(requireActivity(), transactionEntries)
                rv!!.adapter = customAdapter
            })
    }

    companion object {
        private val LOG_TAG = ExpenseFragment::class.java.simpleName
    }
}

private fun <T> LiveData<T>?.observe(expenseFragment: ExpenseFragment, observer: Observer<ArrayList<TransactionEntry>?>) {

}
