package com.example.visita_frias

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Welcome_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrasar el cambio de pantalla por 5 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Loginscreen::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para que no regrese al presionar 'Atrás'
        }, 2000) // 5000 milisegundos = 5 segundos
    }
}
