package com.example.lifecycle

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var bmiTextView: TextView
    private lateinit var recommendationsTextView: TextView
    private lateinit var imageView: ImageView
    private val database = Database()

    @SuppressLint("DefaultLocale", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        bmiTextView = findViewById(R.id.bmi_text)
        recommendationsTextView = findViewById(R.id.recommendations_text)
        imageView = findViewById(R.id.bmi_image)

        val weight = intent.getFloatExtra("weight", 0f)
        val heightCm = intent.getFloatExtra("height", 0f)

        val heightM = heightCm / 100

        val bmi = weight / (heightM * heightM)
        bmiTextView.text = String.format("Ваш ИМТ: %.2f", bmi)

        setRecommendations(bmi)
    }

    private fun setRecommendations(bmi: Float) {
        val weightCategory = when {
            bmi < 18.5 -> Database.WeightCategory.UNDERWEIGHT
            bmi in 18.5..24.9 -> Database.WeightCategory.NORMAL
            bmi in 25.0..29.9 -> Database.WeightCategory.OVERWEIGHT
            bmi in 30.0..39.9 -> Database.WeightCategory.OBESE
            else -> Database.WeightCategory.EXTREMAL_OBESE
        }

        recommendationsTextView.text = database.getRecommendations(weightCategory)

        imageView.setImageResource(when (weightCategory) {
            Database.WeightCategory.UNDERWEIGHT -> R.drawable.underweight
            Database.WeightCategory.NORMAL -> R.drawable.normal
            Database.WeightCategory.OVERWEIGHT -> R.drawable.overweight
            Database.WeightCategory.OBESE -> R.drawable.obese
            Database.WeightCategory.EXTREMAL_OBESE -> R.drawable.extreme_obese
        })
    }
}
