package com.gustavo.gymapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.gustavo.gymapp.data.Calc

class tmbActivity : AppCompatActivity() {
    private lateinit var currentheight: TextView
    private lateinit var currentweight: TextView
    private lateinit var currentage: TextView
    private lateinit var incrementage: ImageView
    private lateinit var decrementage: ImageView
    private lateinit var incrementweight: ImageView
    private lateinit var decrementweight: ImageView
    private lateinit var seekbarforheight: SeekBar
    private lateinit var calculatetbm: Button
    private lateinit var male: RelativeLayout
    private lateinit var female: RelativeLayout
    private lateinit var lifestyle: AutoCompleteTextView

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
        setContentView(R.layout.activity_tbm)

        supportActionBar?.hide()
        currentage = findViewById(R.id.currentage_tbm)
        currentweight = findViewById(R.id.currentweight_tbm)
        currentheight = findViewById(R.id.currentheight_tbm)
        incrementage = findViewById(R.id.incrementage_tbm)
        decrementage = findViewById(R.id.decrementage_tbm)
        incrementweight = findViewById(R.id.incremetweight_tbm)
        decrementweight = findViewById(R.id.decrementweight_tbm)
        calculatetbm = findViewById(R.id.calculatebmi_tbm)
        seekbarforheight = findViewById(R.id.seekbarforheight_tbm)
        male = findViewById(R.id.male_tbm)
        female = findViewById(R.id.female_tbm)
        lifestyle = findViewById(R.id.auto_lifestyle)

        val items = resources.getStringArray(R.array.tbm_lifestyle)
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        lifestyle.setAdapter(adapter)


        male.setOnClickListener {
            male.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.malefemalefocus)
            female.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.malefemalenotfocus)
            typerofuser = "Male"
        }

        female.setOnClickListener {
            female.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.malefemalefocus)
            male.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.malefemalenotfocus)
            typerofuser = "Female"
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

        calculatetbm.setOnClickListener {
            if (typerofuser == "0") {
                Toast.makeText(applicationContext, "Select Your Gender First", Toast.LENGTH_SHORT).show()
            } else if (mintprogress == "0") {
                Toast.makeText(applicationContext, "Select Your Height First", Toast.LENGTH_SHORT).show()
            } else if (intage == 0 || intage < 0) {
                Toast.makeText(applicationContext, "Age is Incorrect", Toast.LENGTH_SHORT).show()
            } else if (intweight == 0 || intweight < 0) {
                Toast.makeText(applicationContext, "Weight Is Incorrect", Toast.LENGTH_SHORT).show()
            } else {

                val result = calculateTmb(intweight,mintprogress.toInt(),intage )
                val response = tmbRequest(result)
                AlertDialog.Builder(this)
                    .setMessage(getString(R.string.tmb_response,response))
                    .setPositiveButton(android.R.string.ok){dialog,which->

                    }
                    .setNegativeButton(R.string.save){dialog,which->
                        Thread{
                            val app = application as App
                            val dao = app.db.calcDao()
                            dao.insert(Calc(type = "tmb", res = response))

                            runOnUiThread {
                                val intent = Intent(this@tmbActivity,ListCalcActivity::class.java)
                                intent.putExtra("type","tmb")
                                startActivity(intent)
                            }
                        }.start()
                    }
                    .create()
                    .show()

                    val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                }
            }
        }
    private fun tmbRequest(tmb: Double) : Double {
        val items = resources.getStringArray(R.array.tbm_lifestyle)
        return when {
            lifestyle.text.toString() == items [1] -> tmb * 1.2
            lifestyle.text.toString() == items [2] -> tmb * 1.375
            lifestyle.text.toString() == items [3] -> tmb * 1.55
            lifestyle.text.toString() == items [4] -> tmb * 1.725
            lifestyle.text.toString() == items [5] -> tmb * 1.9
            else -> 0.0
        }
    }

    private fun calculateTmb(weight: Int, height: Int, age: Int): Double {
        return 66 + (13.8 * weight) + (5 * height) - (6.8 * age)
    }
}




