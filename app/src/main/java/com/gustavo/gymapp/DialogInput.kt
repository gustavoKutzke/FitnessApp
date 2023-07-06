package com.gustavo.gymapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.IllegalArgumentException

class DialogInput :DialogFragment(){

    lateinit var dialogListener :() -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater:LayoutInflater = requireActivity().layoutInflater

            val view:android.view.View = inflater.inflate(R.layout.dialog_input,null)
            builder.setView(view)
                .setPositiveButton(R.string.save){ dialog,which ->
                    save(
                            view.findViewById<TextView>(R.id.txt_value).text.toString().toInt(),
                            view.findViewById<TextView>(R.id.txt_goal).text.toString().toInt()
                    )
                }
                .setNegativeButton(R.string.cancel){dialog,which ->
                    getDialog()?.cancel()
                }
            builder.create()
        }?:throw IllegalArgumentException("Erro")
    }


    private fun save(value:Int,goal:Int){
        val data = hashMapOf(
            "value" to value,
            "goal" to goal
        )
        val db = FirebaseFirestore.getInstance()
        db.collection("data")
            .document(tag!!)
            .set(data)
            .addOnSuccessListener {documentReference->
                dialogListener.invoke()
                println("ID: $documentReference")
            }
            .addOnFailureListener { e->
                println("error ${e.message}")
            }
    }
}