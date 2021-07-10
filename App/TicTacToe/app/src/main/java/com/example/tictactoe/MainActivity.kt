package com.example.tictactoe
import android.content.DialogInterface
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.telecom.PhoneAccount.builder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.stream.IntStream.builder
import kotlin.random.Random

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
        //Log.d("buSelected",buSelected.id.toString())
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
        //Log.d("buClick cellId",cellId.toString())
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

             autoPlay()

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
        var rc=0
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
            player1WinCount++
            Toast.makeText(this,"Player 1 wins !",Toast.LENGTH_LONG).show()
            restartGame()
        }
        else if(winner==2)
        {
            player2WinCount++
            Toast.makeText(this,"Player 2 wins !",Toast.LENGTH_LONG).show()
            restartGame()
        }
        else if(rc==9)
        {
            Toast.makeText(this,"Draw !",Toast.LENGTH_LONG).show()
            restartGame()
        }


    }

    fun autoPlay()
    {
        var emptyCell=ArrayList<Int>()
        for(cellId in 1..9)
        {
            if(!(player1.contains(cellId) || player2.contains(cellId)))
                emptyCell.add(cellId)

        }
        if(emptyCell.size==0)
            restartGame()

        val randomIndex=Random.nextInt(emptyCell.size)
        val cellId=emptyCell[randomIndex]
        val buSelected:Button?
        buSelected=when(cellId){
            1->bu1
            2->bu2
            3->bu3
            4->bu4
            5->bu5
            6->bu6
            7->bu7
            8->bu8
            9->bu9
            else->bu1
        }

        playGame(cellId,buSelected)
    }

    var player1WinCount=0
    var player2WinCount=0

    fun restartGame()
    {
        activePlayer=1
        player1.clear()
        player2.clear()
        for(cellId in 1..9)
        {
            var buSelected:Button?=when(cellId){
                1->bu1
                2->bu2
                3->bu3
                4->bu4
                5->bu5
                6->bu6
                7->bu7
                8->bu8
                9->bu9
                else->bu1
            }
            buSelected!!.text=""
            buSelected!!.setBackgroundResource(R.color.teal_700)
            buSelected.isEnabled=true
        }

        Toast.makeText(this,"Player 1 :$player1WinCount Player 2:$player2WinCount",Toast.LENGTH_LONG).show()


    }
    fun reset(view: View)
    {
        val AlertDialog=AlertDialog.Builder(this)
        AlertDialog.setTitle("Reset")
        AlertDialog.setMessage("Are you sure you want to reset the game?")
        AlertDialog.setPositiveButton("Yes")
        {
            dialogInterface:DialogInterface,i:Int->
            Toast.makeText(this,"Replay",Toast.LENGTH_LONG).show()
            val intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)

        }

        AlertDialog.setNegativeButton("No")
        {
                dialogInterface:DialogInterface,i:Int->
            Toast.makeText(this,"Stop",Toast.LENGTH_LONG).show()

        }
        AlertDialog.show()
    }

}