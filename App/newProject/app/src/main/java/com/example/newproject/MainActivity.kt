package com.example.newproject
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
//
//        val arrayAdapter:ArrayAdapter<*>
//        val participants= arrayOf("A","B","C","D","E")
//        arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,participants)
//        listview.adapter=arrayAdapter

        val imageId= intArrayOf(
            R.drawable.im1,
            R.drawable.im2,
            R.drawable.im3,
            R.drawable.im4,
            R.drawable.im5,

            )
        val name= arrayOf(
            "Kat","Unknown","Mike","Alexa","Michael"
        )

        val lastMessage= arrayOf(
            "Hey","Supp","Gotta go","I'm in a meeting","Let's go"
        )

        val lastMsgtime= arrayOf(
            "8:00 am","10:01 am","12:09 pm","5:00 pm","7:10 pm"
        )

        val country= arrayOf(
            "India","Russia","United States","Germany","Switzerland"
        )

        val phoneNo= arrayOf(
            "9257136231","4357136231","7257136231","1257136231","8257136231"
        )

        userArrayList=ArrayList()
        for (i in name.indices)
        {
            val user=User(name[i],lastMessage[i],lastMsgtime[i],phoneNo[i],country[i],imageId[i])
            userArrayList.add(user)
        }

        binding.listview.isClickable=true
        binding.listview.adapter=MyAdapter(this,userArrayList)

        binding.listview.setOnItemClickListener { parent, view, position, id ->
        val name=name[position]
        val phone=phoneNo[position]
        val country=country[position]
        val imageId=imageId[position]


        val intent=Intent(this,UserActivity::class.java)
        intent.putExtra("name",name)
        intent.putExtra("phone",phone)
        intent.putExtra("country",country)
        intent.putExtra("imageId",imageId)
        startActivity(intent)


      }
    }
}