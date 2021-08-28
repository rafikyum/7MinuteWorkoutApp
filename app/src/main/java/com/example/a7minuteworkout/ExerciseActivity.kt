package com.example.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_custom_bacl_information.*

import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var  player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseStatusAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excerise)

        val toolbar_exericse_activity: Toolbar = findViewById(R.id.toolbar_exercise_activity)
        setSupportActionBar(toolbar_exericse_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_exericse_activity.setNavigationOnClickListener {
            customDialogForBackButton()
        }

        exerciseList = Constants.defaultExerciseList()

        setUpRestView()

        setupExerciseStatusRecyclerView()
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

        if (player != null) {
            player!!.stop()
        }

        super.onDestroy()
    }


    private fun setUpRestView() {

        try {
            player = MediaPlayer.create(applicationContext, R.raw.press_start)
            player!!.isLooping = false
            player!!.start()

        }catch (e: Exception){
            e.printStackTrace()
        }

        val llRestView:LinearLayout = findViewById(R.id.llRestView)
        val llExerciseView:LinearLayout = findViewById(R.id.llExerciseView)

        llRestView.visibility = View.VISIBLE
        llExerciseView.visibility = View.GONE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        val tvUpcomingExerciseName: TextView = findViewById(R.id.tvUpcomingExerciseName)
        tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()

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
                tvTimer.text = (10 - restProgress).toString()               }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
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

        val ivImage : ImageView = findViewById(R.id.ivImage)
        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        val tvExerciseName: TextView = findViewById(R.id.tvExerciseName)
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()



        setExerciseProgressBar()
    }


    private fun setExerciseProgressBar() {

        val progressBarExercise: ProgressBar = findViewById(R.id.progressBarExercise)
        val tvExerciseTimer: TextView = findViewById(R.id.tvExerciseTimer)

        progressBarExercise.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                progressBarExercise.progress = 10 - exerciseProgress
                tvExerciseTimer.text = (10 - exerciseProgress).toString()
            }
            override fun onFinish() {   // when it's finished display message that the exercise is over
                exerciseList!![currentExercisePosition].setIsSelected(false)
                exerciseList!![currentExercisePosition].setIsCompleted(true)
                exerciseAdapter!!.notifyDataSetChanged()
               if(currentExercisePosition < 11) {
                   setUpRestView()
               } else {
                   finish()
                   val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                   startActivity(intent)
                   Toast.makeText(
                       this@ExerciseActivity,
                   "Congratulations! You have completed teh 7 minute workout",
                   Toast.LENGTH_SHORT ).show()
               }
            }
        }.start()
    }

    private fun setupExerciseStatusRecyclerView() {

        val rvExerciseStatus : RecyclerView = findViewById(R.id.rvExerciseStatus)

        rvExerciseStatus.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!, this)

        rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun customDialogForBackButton() {
        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.dialog_custom_bacl_information)
        customDialog.tvYes.setOnClickListener{
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener{
            customDialog.dismiss()
        }
            customDialog.show()
    }


}


