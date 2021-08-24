package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class ExceriseActivity : AppCompatActivity() {
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
    }


}