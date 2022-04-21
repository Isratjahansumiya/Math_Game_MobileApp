package com.example.mathgames

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<View>(R.id.list) as ListView

        val values = arrayOf(
            "How to Play ?",
            "Start Game",
            "EXIT"
        )
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,values)
        listView!!.adapter = adapter

        listView!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            var i: Intent? = null

            when(position){
                0 -> {print("press 0 \n")
                    i = Intent(this@MainActivity,introduction::class.java)
                    startActivity(i)}
            }

            when(position){
                1 -> {
                    print("press 1 \n")
                    i = Intent(this@MainActivity,MathGame::class.java)
                    startActivity(i)
                }
            }
            when(position){
                2 -> {
                    print("press 2 \n")
                    val intent = Intent()
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                    System.exit(0)
                }
            }

        }
    }
}