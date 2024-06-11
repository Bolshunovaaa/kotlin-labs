package com.example.lab5

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener { showMessage("Button One Clicked") }
        binding.btn2.setOnClickListener { showMessage("Button Two Clicked") }

        binding.editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                showMessage("beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showMessage("onTextChanged")
                binding.editText2.setText(s)
            }

            override fun afterTextChanged(s: Editable?) {
                showMessage("afterTextChanged")
            }
        })

        binding.editText2.setOnFocusChangeListener { v, hasFocus ->
            showMessage("EditText 2 focus status: $hasFocus")
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
