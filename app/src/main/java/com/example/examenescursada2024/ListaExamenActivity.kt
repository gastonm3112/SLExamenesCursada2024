package com.example.examenescursada2024

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaExamenActivity : AppCompatActivity() {
    lateinit var rvExamenes: RecyclerView
    lateinit var examenesAdapter: ExamenAdapter
    lateinit var toolbar: Toolbar
    lateinit var tvNoExamenes: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_examen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        tvNoExamenes = findViewById(R.id.tvNoExamenes)
        rvExamenes = findViewById(R.id.rvExamenes)

        examenesAdapter = ExamenAdapter(ArrayList(), this)
        rvExamenes.adapter = examenesAdapter

        CoroutineScope(Dispatchers.IO).launch {
            val examenes = AppDatabase.getDatabase(applicationContext).examenDao().getAll()

            runOnUiThread {
                if (examenes.isEmpty()){
                    tvNoExamenes.visibility = View.VISIBLE
                } else {
                    tvNoExamenes.visibility = View.GONE
                    examenesAdapter.updateExamenes(examenes)
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_agregar_examen, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.agregar_examen){
            val intent = Intent(this, AgregarExamenActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}