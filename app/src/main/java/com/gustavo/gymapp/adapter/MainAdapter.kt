package com.gustavo.gymapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.gymapp.R
import com.gustavo.gymapp.databinding.ServicosItemBinding
import com.gustavo.gymapp.imcActivity
import com.gustavo.gymapp.model.Servicos

class MainAdapter(private val context : Context, private val listMainItens:MutableList<Servicos>,private val onItemClicked:(Int)->Unit):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemList = LayoutInflater.from(context).inflate(R.layout.servicos_item,parent,false)
        val holder = MainViewHolder(itemList)
        return holder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val itemCurrent = listMainItens[position]
        holder.bind(itemCurrent, onItemClicked)
    }

    override fun getItemCount(): Int {
        return listMainItens.size
    }

    class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(item:Servicos,onItemClicked: (Int) -> Unit){
            val img = itemView.findViewById<ImageView>(R.id.imgServicos)
            val txt =itemView.findViewById<TextView>(R.id.txtServicos)
            val container : LinearLayout = itemView.findViewById(R.id.container_home)

            img.setImageResource(item.img!!)
            txt.setText(item.name)

            container.setOnClickListener{
                onItemClicked.invoke(item.id!!)
            }
        }
    }

}