package com.example.mathgames

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
//import nl.dionsegijn.konfetti.KonfettiView
//import nl.dionsegijn.konfetti.models.Shape
//import nl.dionsegijn.konfetti.models.Size

class result : AppCompatActivity() {
    var result: TextView? = null
    var playAgain: ImageButton? = null
    var exit: ImageButton? = null
    //var celebrationView: KonfettiView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val score:Int = intent.getIntExtra("score",0)

        result = findViewById(R.id.textViewResult)
        playAgain = findViewById(R.id.playAgain)
        exit = findViewById(R.id.exit)
        val userScore = score.toString()
        result!!.text = "Your score : "+userScore

        playAgain!!.setOnClickListener{
            var i: Intent? = null
            i = Intent(this@result,MathGame::class.java)
            startActivity(i)
            finish()
        }
        exit!!.setOnClickListener{
            finish()
        }
        //celebrationView = findViewById<KonfettiView>(R.id.celebrationView);
        //celebrationView!!.build().addColors(Color.RED, Color.GREEN, Color.MAGENTA, Color.YELLOW).setDirection(-179.0,359.0).setSpeed(1f,5f).setFadeOutEnabled(true).setTimeToLive(2000L).addShapes(
           // Shape.RECT,Shape.CIRCLE).addSizes(Size(12, 5F)).setPosition(-50f, celebrationView!!.width + 50f, -50f, -50f).streamFor(300, 5000L)
    }

}