package com.example.tictactoe
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent=intent
        val receive:String?=intent.getStringExtra("input")
        textView.text="Welcome $receive!!"


    }

    fun buClick(view: View)
    {
    var buSelected=view as Button
        var cellId=0
        Log.d("buSelected",buSelected.id.toString())
    }

    fun reset(view: View)
    {

    }

}