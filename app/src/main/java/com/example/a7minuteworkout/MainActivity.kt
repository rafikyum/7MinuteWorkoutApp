package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val llStart:LinearLayout = findViewById(R.id.llStart)
        llStart.setOnClickListener{
            Toast.makeText(this, "Here we will start the exercise", Toast.LENGTH_SHORT).show()
        }


    }
}