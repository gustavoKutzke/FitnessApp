package com.gustavo.gymapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.gustavo.gymapp.adapter.MainAdapter
import com.gustavo.gymapp.databinding.ActivityMainBinding
import com.gustavo.gymapp.model.Servicos

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()



        val name = intent.extras?.getString("nome")

        binding.txtnomeMain.text = "Bem-Vindo(a),$name"

        val recyclerViewServicos = binding.rvMain
        recyclerViewServicos.layoutManager = GridLayoutManager(this, 2)
        mainAdapter = MainAdapter(this, listaServicos){id ->
            when (id){
                1 ->{
                    val intent = Intent(this@MainActivity,imcActivity2::class.java)
                    startActivity(intent)
                }
                2->{
                    val intent = Intent(this@MainActivity,tmbActivity::class.java)
                    startActivity(intent)
                }
                3->{
                    val intent = Intent(this@MainActivity,GoalActivity::class.java)
                    startActivity(intent)
                }

            }
            Log.e("Teste","$id")

        }

        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewServicos.adapter = mainAdapter

        val item1 = Servicos(1,R.drawable.ic_home,"IMC")
        listaServicos.add(item1)

        val item2 = Servicos(2,R.drawable.ic_home,"TBM")
        listaServicos.add(item2)

        val item3 = Servicos(3,R.drawable.ic_home,"GOAL")
        listaServicos.add(item3)

    }



}