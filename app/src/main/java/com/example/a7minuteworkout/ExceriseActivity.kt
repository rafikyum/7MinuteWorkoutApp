package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import org.w3c.dom.Text

class ExceriseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0                        // timers and progress created

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimeDuration: Long = 30

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excerise)

        val toolbar_exericse_activity: Toolbar = findViewById(R.id.toolbar_exercise_activity)
        setSupportActionBar(toolbar_exericse_activity)          // brings fourth toolbar activity

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_exericse_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpRestView()

        exerciseList = Constants.defaultExerciseList()
    }


    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0        // if the timer is over cancel the timer and reset progress of the timer
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()  // if exercise timer is over end the timer and reset the progress of the timer
            exerciseProgress = 0
        }

        super.onDestroy()
    }

    private fun setUpRestView() {
        val llRestView : LinearLayout = findViewById(R.id.llRestView)
        val llExerciseView: LinearLayout = findViewById(R.id.llExerciseView)
        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE


        if (restTimer != null) {
            restTimer!!.cancel()        // set up rest view if the rest timer is not null
            restProgress = 0;
        }

        setRestProgressBar()        // once the timer has been reset bring fourth the progress bar
    }


    private fun setRestProgressBar() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tvTimer: TextView = findViewById(R.id.tvTimer)          // instances of the timer and progress bar from the xml file
        progressBar.progress = restProgress     // progress bar of xml is set to an instance of the rest progress created in activity
        restTimer = object : CountDownTimer(10000, 1000) {  // rest timer becomes countdown timer and counts down second at a time
            override fun onTick(p0: Long) { // per tick add to the progress of rest bar
                restProgress++
                progressBar.progress = 10 - restProgress     // makes the actual timer go down on the xml side of things
                tvTimer.text = (10 - restProgress).toString()   // display the text view and the time on it

            }

            override fun onFinish() {
                currentExercisePosition++
                setupExerciseView()     // call the exercise view when the rest side of things is finished
            }
        }.start()
    }



    private fun setupExerciseView() {

        val llRestView: LinearLayout = findViewById(R.id.llRestView)
        val llExerciseView: LinearLayout = findViewById(R.id.llExerciseView)        // instances of the rest view and exercise from the xml side

        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE    // gets rid of the rest view and brings fourth the exercise view


        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()    // if the exercise timer for some reason isn't null then reset everything , more so a precaution
            exerciseProgress = 0
        }

        setExerciseProgressBar()    // bring fourth the exercise progress bar method

        val ivImage : ImageView = findViewById(R.id.ivImage)
        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        val tvExerciseName: TextView = findViewById(R.id.tvExerciseName)
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()

    }


    private fun setExerciseProgressBar() {

        val progressBarExercise: ProgressBar = findViewById(R.id.progressBarExercise)
        val tvExerciseTimer: TextView = findViewById(R.id.tvExerciseTimer)              // instances of the exercise time and progress bar time from the xml side

        progressBarExercise.progress = exerciseProgress     // progress of exercise

        exerciseTimer = object : CountDownTimer(30000, 1000) {  // exercise timer becomes count down timer and creates the timer
            override fun onTick(millisUntilFinished: Long) {        // creates displays the timer going down basically
                exerciseProgress++
                progressBarExercise.progress = 30 - exerciseProgress
                tvExerciseTimer.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {   // when it's finished display message that the exercise is over
               if(currentExercisePosition < 11) {
                   setUpRestView()
               } else {
                   Toast.makeText(
                       this@ExceriseActivity,
                   "Congratulations! You have completed teh 7 minute workout",
                   Toast.LENGTH_SHORT ).show()
               }
            }
        }.start()
    }
}


