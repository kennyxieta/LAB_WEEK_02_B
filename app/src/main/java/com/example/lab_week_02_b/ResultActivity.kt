package com.example.lab_week_02_b

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COLOR = "COLOR_KEY"
        const val EXTRA_ERROR = "ERROR_MSG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bg  = findViewById<ConstraintLayout>(R.id.background_screen)
        val msg = findViewById<TextView>(R.id.color_code_result_message)

        val code = intent.getStringExtra(EXTRA_COLOR).orEmpty()

        try {
            bg.setBackgroundColor(Color.parseColor("#$code"))
            msg.text = getString(R.string.color_code_result_message, code.uppercase())
            setResult(RESULT_OK)
        } catch (e: IllegalArgumentException) {
            // kalau ada yang nyelip & parse gagal, balikkan error (tanpa crash)
            setResult(
                RESULT_CANCELED,
                Intent().putExtra(EXTRA_ERROR, getString(R.string.invalid_hex_message))
            )
            finish()
        }
    }
}
