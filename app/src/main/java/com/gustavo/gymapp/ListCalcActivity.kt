package com.gustavo.gymapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.gymapp.data.Calc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class ListCalcActivity : AppCompatActivity() {

    private lateinit var rv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calc)

        val result = mutableListOf<Calc>()
        val adapter = ListCalcAdapter(result)
        rv= findViewById(R.id.rv_list)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter


        val type = intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")

//        Thread {
//            val app = application as App
//            val dao = app.db.calcDao()
//            val response = dao.getRegisterByType(type)
//
//
//            runOnUiThread {
//                result.addAll(response)
//                adapter.notifyDataSetChanged()
//            }
//        }
        
         GlobalScope.launch(Dispatchers.IO) {
                    val app = application as App
                    val dao = app.db.calcDao()
                    val response = dao.getRegisterByType(type)
                    withContext(Dispatchers.Main) {
                        result.addAll(response)
                        adapter.notifyDataSetChanged()
                    }
                }
    }

    private inner class ListCalcAdapter(
        private val listCalc:List<Calc>,
    ):RecyclerView.Adapter<ListCalcViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCalcViewHolder {
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false)
            return ListCalcViewHolder(view)

        }

        override fun onBindViewHolder(holder: ListCalcViewHolder, position: Int) {
            val itemCurrent = listCalc[position]
            holder.bind(itemCurrent)
        }

        override fun getItemCount(): Int {
            return listCalc.size
        }
    }

    private inner class ListCalcViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(item:Calc){
            val tv = itemView as TextView

            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt","BR"))
            val date = sdf.format(item.createDate)
            val res = item.res

            tv.text = getString(R.string.list_response,res,date)
        }
    }


}