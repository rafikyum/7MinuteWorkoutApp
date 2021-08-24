package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import org.w3c.dom.Text

class ExceriseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excerise)

        val toolbar_exericse_activity:Toolbar = findViewById(R.id.toolbar_exercise_activity)
        setSupportActionBar(toolbar_exericse_activity)
        val actionbar = supportActionBar
        if (actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exericse_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpRestView()

    }

    private fun setUpRestView(){
        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0;
        }

        setRestProgressBar()
    }



    private fun setRestProgressBar() {
        val progressBar:ProgressBar = findViewById(R.id.progressBar)
        val tvTimer: TextView = findViewById(R.id.tvTimer)
        progressBar.progress = restProgress
        restTimer = object: CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = 10-restProgress
                tvTimer.text = (10-restProgress).toString()
            }

            override fun onFinish() {
               Toast.makeText(this@ExceriseActivity,
               "Here now we will start the exercise.",
               Toast.LENGTH_SHORT
               ).show()
            }
        }.start()
    }



    override fun onDestroy() {
        if(restTimer !=null){
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }



}