package com.gustavo.gymapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class imcActivity : AppCompatActivity() {
    private lateinit var bmidisplay: TextView
    private lateinit var bmicategory: TextView
    private lateinit var gotomain: Button
    private lateinit var imageview: ImageView
    private lateinit var background: RelativeLayout
    private lateinit var gender: TextView

    private lateinit var activityIntent: Intent

    private var mbmi: String? = null
    private var category: String? = null
    private var intbmi: Float = 0.toFloat()

    private var height: String? = null
    private var weight: String? = null

    private var intheight: Float = 0.0F
    private var intweight: Float = 0.0F

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)
        supportActionBar?.elevation = 0f
        val colorDrawable = ColorDrawable(Color.parseColor("#1E1D1D"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)

        supportActionBar?.title = Html.fromHtml("<font color=\"white\"></font>")
        supportActionBar?.title = "Result"

        intent = intent
        bmidisplay = findViewById(R.id.bmidisplay)
        bmicategory = findViewById(R.id.bmicategorydispaly)
        gotomain = findViewById(R.id.gotomain)
        imageview = findViewById(R.id.imageview)
        background = findViewById(R.id.contentlayout)
        gender = findViewById(R.id.genderdisplay)

        height = intent.getStringExtra("height")
        weight = intent.getStringExtra("weight")

        intheight = height?.toFloat()?.div(100) ?: 0f
        intweight = weight?.toFloat() ?: 0f

        intbmi = intweight / (intheight * intheight)

        mbmi = intbmi.toString()
        println(mbmi)

        if (intbmi < 16) {
            bmicategory.text = "Magreza Extrema"
            background.setBackgroundColor(Color.RED)
            imageview.setImageResource(R.drawable.morto)
        } else if (intbmi < 16.9 && intbmi > 16) {
            bmicategory.text = "Muito Abaixo do peso"
            background.setBackgroundColor(R.color.halfwarn)
            imageview.setImageResource(R.drawable.chocado)
        } else if (intbmi < 18.4 && intbmi > 17) {
            bmicategory.text = "Abaixo do Peso"
            background.setBackgroundColor(R.color.halfwarn)
            imageview.setImageResource(R.drawable.chocado)
        } else if (intbmi < 24.9 && intbmi > 18.5) {
            bmicategory.text = "Normal"
            imageview.setImageResource(R.drawable.feliz)
        } else if (intbmi < 29.9 && intbmi > 25) {
            bmicategory.text = "Acima do Peso"
            background.setBackgroundColor(R.color.halfwarn)
            imageview.setImageResource(R.drawable.chocado)
        } else if (intbmi < 34.9 && intbmi > 30) {
            bmicategory.text = "Obesidade I"
            background.setBackgroundColor(R.color.halfwarn)
            imageview.setImageResource(R.drawable.chocado)
        } else {
            bmicategory.text = "Obesidade II"
            background.setBackgroundColor(R.color.warn)
            imageview.setImageResource(R.drawable.morto)
        }

        gender.setText(intent.getStringExtra("gender"))
        bmidisplay.setText(mbmi)

        gotomain.setOnClickListener {
            val intent1 = Intent(applicationContext,imcActivity2::class.java)
            startActivity(intent1)
        }
    }
}