package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN

    val tv2:Button=findViewById(R.id.btn_start)
        tv2.setOnClickListener {
            val tv3:EditText=findViewById(R.id.et_name)
            if(tv3.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent=Intent(this,QuizQuestionsActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}