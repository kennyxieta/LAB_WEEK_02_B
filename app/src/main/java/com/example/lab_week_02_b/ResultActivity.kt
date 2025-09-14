package com.example.lab_week_02_b

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {
    companion object { const val COLOR_KEY = "COLOR_KEY" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bg = findViewById<ConstraintLayout>(R.id.background_screen)
        val msg = findViewById<TextView>(R.id.color_code_result_message)

        val code = intent.getStringExtra(COLOR_KEY).orEmpty()
        bg.setBackgroundColor(Color.parseColor("#$code"))
        msg.text = getString(R.string.color_code_result_message, code.uppercase())
    }
}
