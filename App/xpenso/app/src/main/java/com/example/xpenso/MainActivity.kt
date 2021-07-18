package com.example.xpenso

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private var mViewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.container)
        setupViewPager(mViewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(mViewPager)
        fab!!.setOnClickListener {
            CustomBottomSheetDialogFragment().show(
                supportFragmentManager,
                "Dialog"
            )
        }
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = SectionsPageAdapter(supportFragmentManager)
        adapter.addFragment(ExpenseFragment(), "Expenses")
        adapter.addFragment(BalanceFragment(), "Balance")
        viewPager!!.adapter = adapter
    }

    companion object {
        var fab: FloatingActionButton? = null
    }
}