package com.gustavo.gymapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.gustavo.gymapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener{
            val name = binding.edtName.text.toString()
            val password = binding.edtPassword.text.toString()

            when{
                name.isEmpty() ->{
                    message(it,"Coloque seu Nome")

                }password.isEmpty()->{
                    message(it,"Coloque sua Senha")
                }password.length <=5 ->{
                    message(it,"A senha precisa ter pelo menos 6 caracteres")
                }else ->{
                    goHome(name)
                }
            }
        }
    }

    private fun message(view: View, mensagem:String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    private fun goHome(name:String){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("nome",name)
        startActivity(intent)
    }
}