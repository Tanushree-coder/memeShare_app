package com.example.xpenso

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.xpenso.Constants.addExpenseString
import com.example.xpenso.Constants.addIncomeString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_fragment.*


class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {
    var addIncomeButton: Button? = null
    var addExpenseButton: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.bottom_sheet_fragment, container, false)

        bottom_sheet_income_btn.setOnClickListener {
            dismiss()
            val intent = Intent(activity, AddExpenseActivity::class.java)
            intent.putExtra("from", addIncomeString)
            startActivity(intent)
        }
        bottom_sheet_expense_btn.setOnClickListener{
            dismiss()
            val intent = Intent(activity, AddExpenseActivity::class.java)
            intent.putExtra("from", addExpenseString)
            startActivity(intent)
        }
        return v
    }
}