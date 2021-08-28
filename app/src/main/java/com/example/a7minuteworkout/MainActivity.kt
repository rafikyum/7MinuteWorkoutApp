package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val llStart:LinearLayout = findViewById(R.id.llStart)
        llStart.setOnClickListener{
            Toast.makeText(this, "Here we will start the exercise", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        val llBMI : LinearLayout = findViewById(R.id.llBMI)
        llBMI.setOnClickListener {
            // Launching the BMI Activity
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }

        llHistory.setOnClickListener {
            // Launching the History Activity
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }
}