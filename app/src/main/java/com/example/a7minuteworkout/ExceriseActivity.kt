package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import org.w3c.dom.Text

class ExceriseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excerise)

        val toolbar_exericse_activity: Toolbar = findViewById(R.id.toolbar_exercise_activity)
        setSupportActionBar(toolbar_exericse_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_exericse_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpRestView()

    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        super.onDestroy()
    }

    private fun setUpRestView() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0;
        }

        setRestProgressBar()
    }


    private fun setRestProgressBar() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tvTimer: TextView = findViewById(R.id.tvTimer)
        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = 10 - restProgress
                tvTimer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                setupExerciseView()
            }
        }.start()
    }



    private fun setupExerciseView() {

        val llRestView: LinearLayout = findViewById(R.id.llRestView)
        val llExerciseView: LinearLayout = findViewById(R.id.llExerciseView)

        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE


        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        setExerciseProgressBar()
    }


    private fun setExerciseProgressBar() {

        val progressBarExercise: ProgressBar = findViewById(R.id.progressBarExercise)
        val tvExerciseTimer: TextView = findViewById(R.id.tvExerciseTimer)

        progressBarExercise.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress = 30 - exerciseProgress
                tvExerciseTimer.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExceriseActivity,
                    "This is 30 seconds completed so now we will add all the exercises.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }
}


