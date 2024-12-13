package com.example.lifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var weightInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        weightInput = findViewById(R.id.weightET)
        heightInput = findViewById(R.id.heightET)
        calculateButton = findViewById(R.id.calculateBTN)

        calculateButton.setOnClickListener {
            val weight = weightInput.text.toString().toFloatOrNull()
            val height = heightInput.text.toString().toFloatOrNull()

            if (weight == null || height == null || weight <= 0 || height <= 0) {
                Toast.makeText(this, "Пожалуйста, введите корректные значения для веса и роста.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("weight", weight)
                intent.putExtra("height", height)
                startActivity(intent)
            }
        }
    }
}
