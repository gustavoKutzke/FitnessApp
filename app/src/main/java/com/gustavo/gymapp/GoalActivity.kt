package com.gustavo.gymapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.firebase.firestore.FirebaseFirestore

class GoalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        refresh()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh ->{
                refresh()
            }
            else->{
                DialogInput().apply {
                    dialogListener ={
                        refresh()
                    }
                    show(supportFragmentManager,item.title.toString())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun refresh(){
        val progress = findViewById<ProgressBar>(R.id.progressbar)
        progress.visibility = View.VISIBLE

        FirebaseFirestore.getInstance().collection("data")
            .get()
            .addOnSuccessListener { result->
                for(document in result){
                    val value = document.data["value"] as Long
                    val goal = document.data["goal"] as Long

                    val res = value.toFloat() * 100/goal.toFloat()
                    val txt ="$value/$goal"

                    when(document.id){
                        getString(R.string.calories)->{
                            val txt_calories = findViewById<TextView>(R.id.txt_calories)
                            txt_calories.text = txt
                            val progress1 = findViewById<CustomProgressbar>(R.id.progress1)
                            progress1.setValue(res.toInt())
                        }
                        getString(R.string.protein)->{
                            val txt_protein = findViewById<TextView>(R.id.txt_protein)
                            txt_protein.text = txt
                            val progress2 = findViewById<CustomProgressbar>(R.id.progress2)
                            progress2.setValue(res.toInt())
                        }
                        getString(R.string.carbohydrate)->{
                            val txt_carbohydrate = findViewById<TextView>(R.id.txt_carbohydrate)
                            txt_carbohydrate.text = txt
                            val progress3 = findViewById<CustomProgressbar>(R.id.progress3)
                            progress3.setValue(res.toInt())
                        }
                    }
                }
            }
            .addOnFailureListener { exeption ->
                println("error")
            }
            .addOnCompleteListener {
                progress.visibility = View.GONE
            }
    }

}