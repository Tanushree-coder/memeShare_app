package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(),View.OnClickListener {

    private var mCurrentPosition:Int=1
    private var mQuestionList:ArrayList<Question>? =null
    private var mSelectedOptionPosition:Int=0

    var op1:TextView=findViewById(R.id.tv_option_one)

    var op2:TextView=findViewById(R.id.tv_option_two)

    var op3:TextView=findViewById(R.id.tv_option_three)

    var op4:TextView=findViewById(R.id.tv_option_four)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        op1.setOnClickListener(this)
        op2.setOnClickListener(this)
        op3.setOnClickListener(this)
        op4.setOnClickListener(this)

        setQuestion()

    }
    private fun setQuestion()
    {
        mQuestionList=Constants.getQuestions()
        val ques:Question=mQuestionList!![mCurrentPosition-1]

        val progBar:ProgressBar=findViewById(R.id.progressBar)
        progBar.progress=mCurrentPosition
        val tvprogBar:TextView=findViewById(R.id.tv_progress)
        tvprogBar.text="${mCurrentPosition}"+"/"+progBar.max
        val tvques:TextView=findViewById(R.id.tv_question)
        tvques.text=ques.question
        val tvimg:ImageView=findViewById(R.id.tv_image)
        tvimg.setImageResource(ques.image)
        //var tv_op1:TextView=findViewById(R.id.tv_option_one)
        op1.text=ques.optionOne
        //var tv_op2:TextView=findViewById(R.id.tv_option_two)
        op2.text=ques.optionTwo
        //var tv_op3:TextView=findViewById(R.id.tv_option_three)
        op3.text=ques.optionThree
        //var tv_op4:TextView=findViewById(R.id.tv_option_four)
        op4.text=ques.optionFour

    }

    private fun defaultOptionsView()
    {
        val options=ArrayList<TextView>()
        options.add(0,op1)
        options.add(1,op2)
        options.add(2,op3)
        options.add(3,op4)
        for(option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)

        }

    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.tv_option_one->{
                selectedOptionview(op1,1)
            }
            R.id.tv_option_two->{
                selectedOptionview(op2,2)
            }
            R.id.tv_option_three->{
                selectedOptionview(op3,3)
            }
            R.id.tv_option_four->{
                selectedOptionview(op4,4)
            }

        }
    }

    private fun selectedOptionview (tv:TextView,selectedOptionNum:Int)
    {
        defaultOptionsView()

        mSelectedOptionPosition=selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)


    }


}
