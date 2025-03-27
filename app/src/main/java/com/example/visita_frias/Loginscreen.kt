package com.example.visita_frias

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Loginscreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginscreen)

        // Inicializa Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Obtén referencias a los campos de usuario, contraseña y botón
        val usernameField = findViewById<EditText>(R.id.username)
        val passwordField = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)

        // Configura el listener del botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = usernameField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Valida los campos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llama a Firebase para autenticar al usuario
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Inicio de sesión exitoso
                        Toast.makeText(this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()
                        navigateToHomeScreen()
                    } else {
                        // Error en el inicio de sesión
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    // Método para navegar a la pantalla principal
    private fun navigateToHomeScreen() {
        val intent = Intent(this, Mainhome::class.java)
        startActivity(intent)
        finish() // Finaliza esta actividad para que no se pueda volver con el botón "Atrás"
    }
}
