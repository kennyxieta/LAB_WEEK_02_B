package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.submit_button)
        val input = findViewById<TextInputEditText>(R.id.color_code_input_field)

        btn.setOnClickListener {
            val code = input.text?.toString().orEmpty()
            if (code.isEmpty()) {
                Toast.makeText(this, getString(R.string.color_code_input_empty), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (code.length < 6) {
                Toast.makeText(this, getString(R.string.color_code_input_wrong_length), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(ResultActivity.COLOR_KEY, code)
            startActivity(intent)
        }
    }
}
