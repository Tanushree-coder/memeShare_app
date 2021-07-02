package com.example.tictactoe
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

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
        when(buSelected.id)
        {
            R.id.bu1->cellId=1
            R.id.bu2->cellId=2
            R.id.bu3->cellId=3
            R.id.bu4->cellId=4
            R.id.bu5->cellId=5
            R.id.bu6->cellId=6
            R.id.bu7->cellId=7
            R.id.bu8->cellId=8
            R.id.bu9->cellId=9
        }
        Log.d("buClick cellId",cellId.toString())
        playGame(cellId,buSelected)
    }

    var activePlayer=1
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()

    fun playGame(cellId:Int,buSelected:Button)
    {
        if(activePlayer==1)
        {
            buSelected.text="X"
            buSelected.setBackgroundResource(R.color.purple_500)
            player1.add(cellId)
            activePlayer=2

        }
        else
        {
            buSelected.text="0"
            buSelected.setBackgroundResource(R.color.teal_700)
            player2.add(cellId)
            activePlayer=1

        }
        buSelected.isEnabled=false
        checkWinner()

    }
    fun checkWinner()
    {
        var winner=-1
        var rc=1
        // ROW 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3))
            winner=1
        else if(player2.contains(1) && player2.contains(2) && player2.contains(3))
            winner=2
        // ROW 2
        else if(player1.contains(4) && player1.contains(5) && player1.contains(6))
            winner=1
        else if(player2.contains(4) && player2.contains(5) && player2.contains(6))
            winner=2
        // ROW 3
        else if(player1.contains(7) && player1.contains(8) && player1.contains(9))
            winner=1
        else if(player2.contains(7) && player2.contains(8) && player2.contains(9))
            winner=2

        // COLUMN 1
        else if(player1.contains(1) && player1.contains(4) && player1.contains(7))
            winner=1
        else if(player2.contains(1) && player2.contains(4) && player2.contains(7))
            winner=2
        // COLUMN 2
        else if(player1.contains(2) && player1.contains(5) && player1.contains(8))
            winner=1
        else if(player2.contains(2) && player2.contains(5) && player2.contains(8))
            winner=2
        // COLUMN 3
        else if(player1.contains(3) && player1.contains(6) && player1.contains(9))
            winner=1
        else if(player2.contains(3) && player2.contains(6) && player2.contains(9))
            winner=2
        // DIAGONAL 1
        else if(player1.contains(1) && player1.contains(5) && player1.contains(9))
            winner=1
        else if(player2.contains(1) && player2.contains(5) && player2.contains(9))
            winner=2
        // DIAGONAL 2
        else if(player1.contains(3) && player1.contains(5) && player1.contains(7))
            winner=1
        else if(player2.contains(3) && player2.contains(5) && player2.contains(7))
            winner=2
        rc++
        if(winner==1)
        {
            Toast.makeText(this,"Player 1 wins !",Toast.LENGTH_LONG).show()
        }
        else if(winner==2)
        {
            Toast.makeText(this,"Player 2 wins !",Toast.LENGTH_LONG).show()
        }
        else if(rc==9)
        {
            Toast.makeText(this,"Draw !",Toast.LENGTH_LONG).show()
        }


    }


    fun reset(view: View)
    {

    }

}