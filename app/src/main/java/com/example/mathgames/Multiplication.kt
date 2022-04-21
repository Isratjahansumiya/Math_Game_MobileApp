package com.example.mathgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import java.util.*

class Multiplication : AppCompatActivity() {
    var score: TextView? = null
    var life: TextView? = null
    var time: TextView? = null

    var question: TextView? = null
    var answer: EditText? = null

    var ok: ImageButton? = null
    var next: ImageButton? = null
    val random = Random()
    var number1 =0
    var number2 =0
    var useranswer =0
    var realanswer =0
    var userScore =0
    var userLife =3
    var timer : CountDownTimer? = null
    var timer_running = true
    var START_TIMER :Long = 20000
    var TIME_LEFT = START_TIMER
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        score = findViewById(R.id.textViewScore)
        life = findViewById(R.id.textViewLife)
        time = findViewById(R.id.textViewTime)


        question = findViewById(R.id.question)
        answer = findViewById(R.id.answer)

        ok = findViewById(R.id.buttonok)
        next = findViewById(R.id.buttonnext)
        gameContinue()
        ok!!.setOnClickListener{
            val new = answer!!.text.toString()
            if(new.isNotEmpty()){
                useranswer = Integer.valueOf(new)
            }else{
                Toast.makeText(applicationContext,"Please write the number", Toast.LENGTH_LONG).show()
            }
            //useranswer = Integer.valueOf(answer!!.text.toString())

            pauseTimer()
            if (useranswer ==realanswer){
                userScore += 10
                score!!.text = " "+userScore
                question!!.text = "Congratulation, Your answer is true."
            }
            else{
                userLife -=1
                life!!.text = " "+userLife
                question!!.text = "Sorry, your answer is wrong"
            }
        }

        next!!.setOnClickListener{
            answer!!.setText("")
            resetTimer()

            if(userLife<=0){
                Toast.makeText(applicationContext,"Game Over", Toast.LENGTH_LONG).show()
                var i: Intent? = null
                i = Intent(this@Multiplication,result::class.java)
                i.putExtra("score",userScore)
                startActivity(i)
                finish()
            }
            else{
                gameContinue()
            }
        }
    }
    fun gameContinue(){
        number1 = random.nextInt(100)
        number2 = random.nextInt(100)
        realanswer = number1 * number2
        question!!.text  = number1.toString()+ " x "+ number2.toString()
        startTimer()
    }
    fun startTimer(){
        timer = object : CountDownTimer(TIME_LEFT,1000){
            override fun onTick(millisUntilFinished: Long) {

                TIME_LEFT = millisUntilFinished
                updateText()
            }

            override fun onFinish() {

                timer_running = false
                pauseTimer()
                resetTimer()
                updateText()
                userLife -=1
                life!!.text = ""+userLife
                question!!.text = "Sorry! Time is up"
            }

        }.start()
        timer_running = true
    }
    fun updateText(){
        var second = ((TIME_LEFT /1000) %60).toInt()
        var time_left = String.format(Locale.getDefault(),"%02d",second)
        time!!.text = time_left
    }

    fun pauseTimer(){
        timer!!.cancel()
        timer_running = false
    }
    fun resetTimer(){
        TIME_LEFT = START_TIMER
        updateText()
    }
}