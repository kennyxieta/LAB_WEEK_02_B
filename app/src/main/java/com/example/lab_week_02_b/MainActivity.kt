package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    // launcher untuk menerima hasil dari ResultActivity
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode != RESULT_OK) {
            val msg = result.data?.getStringExtra(ResultActivity.EXTRA_ERROR)
                ?: getString(R.string.invalid_hex_message)
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputWrapper = findViewById<TextInputLayout>(R.id.color_code_input_wrapper)
        val inputField   = findViewById<TextInputEditText>(R.id.color_code_input_field)
        val btn          = findViewById<Button>(R.id.submit_button)

        // clear error saat user mengetik
        inputField.doOnTextChanged { _, _, _, _ -> inputWrapper.error = null }

        btn.setOnClickListener {
            val raw = inputField.text?.toString().orEmpty().trim()
            val code = raw.removePrefix("#") // terima input dengan/ tanpa '#'

            // Validasi: harus 6 digit hex
            val isValid = code.length == 6 && code.matches(Regex("^[0-9a-fA-F]{6}$"))
            if (!isValid) {
                inputWrapper.error = getString(R.string.invalid_hex_message)
                Toast.makeText(this, getString(R.string.fixed_input_message), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Lolos → kirim ke ResultActivity via Activity Result API
            val intent = Intent(this, ResultActivity::class.java)
                .putExtra(ResultActivity.EXTRA_COLOR, code)
            resultLauncher.launch(intent)
        }
    }
}
