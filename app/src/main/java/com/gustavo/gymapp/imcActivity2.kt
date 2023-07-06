package com.gustavo.gymapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class imcActivity2 : AppCompatActivity() {
    private lateinit var currentheight: TextView
    private lateinit var currentweight: TextView
    private lateinit var currentage: TextView
    private lateinit var incrementage: ImageView
    private lateinit var decrementage: ImageView
    private lateinit var incrementweight: ImageView
    private lateinit var decrementweight: ImageView
    private lateinit var seekbarforheight: SeekBar
    private lateinit var calculatebmi: Button
    private lateinit var male: RelativeLayout
    private lateinit var female: RelativeLayout


    private var intweight = 55
    private var intage = 22
    private var currentprogress: Int = 0
    private var mintprogress = "170"
    private var typerofuser = "0"
    private var weight2 = "55"
    private var age2 = "22"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc2)

        supportActionBar?.hide()
        currentage = findViewById(R.id.currentage)
        currentweight = findViewById(R.id.currentweight)
        currentheight = findViewById(R.id.currentheight)
        incrementage = findViewById(R.id.incrementage)
        decrementage = findViewById(R.id.decrementage)
        incrementweight = findViewById(R.id.incremetweight)
        decrementweight = findViewById(R.id.decrementweight)
        calculatebmi = findViewById(R.id.calculatebmi)
        seekbarforheight = findViewById(R.id.seekbarforheight)
        male = findViewById(R.id.male)
        female = findViewById(R.id.female)



        male.setOnClickListener {
            male.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.malefemalefocus)
            female.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.malefemalenotfocus)
            typerofuser = "Homem"
        }

        female.setOnClickListener {
            female.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.malefemalefocus)
            male.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.malefemalenotfocus)
            typerofuser = "Femenino"
        }

        seekbarforheight.max = 300
        seekbarforheight.progress = 170
        seekbarforheight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                currentprogress = progress
                mintprogress = currentprogress.toString()
                currentheight.text = mintprogress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        incrementweight.setOnClickListener {
            intweight += 1
            weight2 = intweight.toString()
            currentweight.text = weight2
        }

        incrementage.setOnClickListener {
            intage += 1
            age2 = intage.toString()
            currentage.text = age2
        }

        decrementage.setOnClickListener {
            intage -= 1
            age2 = intage.toString()
            currentage.text = age2
        }

        decrementweight.setOnClickListener {
            intweight = intweight - 1
            weight2 = intweight.toString()
            currentweight.text = weight2
        }

        calculatebmi.setOnClickListener {
            if (typerofuser == "0") {
                Toast.makeText(applicationContext, "Selecione Seu Gênero Primeiro !", Toast.LENGTH_SHORT).show()
            } else if (mintprogress == "0") {
                Toast.makeText(applicationContext, "Selecione Sua Altura Primeiro !", Toast.LENGTH_SHORT).show()
            } else if (intage == 0 || intage < 0) {
                Toast.makeText(applicationContext, "A sua idade está errada", Toast.LENGTH_SHORT).show()
            } else if (intweight == 0 || intweight < 0) {
                Toast.makeText(applicationContext, "Seu peso esta errado !", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@imcActivity2, imcActivity::class.java)
                intent.putExtra("gender", typerofuser)
                intent.putExtra("height", mintprogress)
                intent.putExtra("weight", weight2)
                intent.putExtra("age", age2)
                startActivity(intent)
            }
        }
    }
}