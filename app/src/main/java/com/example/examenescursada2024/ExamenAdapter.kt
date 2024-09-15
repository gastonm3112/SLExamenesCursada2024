package com.example.examenescursada2024

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ExamenAdapter(var examenes: MutableList<Examen>, var context:Context):
  RecyclerView.Adapter<ExamenAdapter.ExamenViewHolder>(){

    class ExamenViewHolder(view: View): RecyclerView.ViewHolder(view){
      val txtMateria: TextView
      val txtFecha: TextView

      init{
        txtMateria = view.findViewById(R.id.tv_materia)
        txtFecha = view.findViewById(R.id.tv_fecha)
      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamenViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_examen, parent, false)
        return ExamenViewHolder(view)
    }

    override fun getItemCount() = examenes.size

    fun updateExamenes(nuevosExamenes: MutableList<Examen>) {
        this.examenes = nuevosExamenes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ExamenViewHolder, position: Int) {
        val item = examenes[position]
        holder.txtMateria.text = item.materia
        holder.txtFecha.text = item.fecha

        //Configuracion del clic en cada item
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Materia: ${item.materia}", Toast.LENGTH_SHORT).show()
        }
    }

  }