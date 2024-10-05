package com.example.evaluacion1rodrigoretamal

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var spinnerFood: Spinner
    private lateinit var radioGroupFrequency: RadioGroup
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Elementos del formulario
        editTextName = findViewById(R.id.texteditPreferencesName)
        spinnerFood = findViewById(R.id.spinnerFavFood)
        radioGroupFrequency = findViewById(R.id.radgroupPreferencesForm)
        buttonSubmit = findViewById(R.id.buttonPrefSummit)

        // Manejo del Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.preferences_spinner_items,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFood.adapter = adapter

        // Listener Summit
        buttonSubmit.setOnClickListener {
            enviarFormulario()
        }
    }

    private fun enviarFormulario() {
        // Obtención de valores
        val name = editTextName.text.toString().trim()
        val favoriteFood = spinnerFood.selectedItem.toString()
        val selectedFrequencyId = radioGroupFrequency.checkedRadioButtonId

        // Validación de campos
        if (name.isEmpty() || selectedFrequencyId == -1) {
            Toast.makeText(this, "Por favor completa todos los campos del formulario!", Toast.LENGTH_SHORT).show()
            return
        }

        // Obtener el texto del RadioButton seleccionado
        val selectedFrequency = findViewById<RadioButton>(selectedFrequencyId).text.toString()

        // Resumen
        val summary = "Nombre: $name\nComida Favorita: $favoriteFood\nFrecuencia de Consumo: $selectedFrequency"

        // Mostrando resumen en un Toast
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container))
        val textView: TextView = layout.findViewById(R.id.custom_toast_message)
        textView.text = summary
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
        Toast.makeText(this, summary, Toast.LENGTH_LONG).show()
    }
}
