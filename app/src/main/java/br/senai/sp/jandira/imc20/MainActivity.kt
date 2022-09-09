package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val openSingUpActivity = Intent(this, SingUpActivity::class.java)

        binding.textViewSingUp.setOnClickListener {
            // call the other activity                            // Instance an object
            startActivity(openSingUpActivity)
        }

        binding.buttonSingIn.setOnClickListener {
            login()
        };

    }

    private fun login() {
        if(validateLogin()) {
            val email = binding.editTextEmailAddresSingIn.text.toString();
            val password = binding.editTextPasswordSingIn.text.toString();
            // shared preferences

            val dados = getSharedPreferences("dados", MODE_PRIVATE)
            val emailSp = dados.getString("email", "Email nao encontrado")
            val passwordSp = dados.getString("password", "")
            if (email == emailSp && password == passwordSp) {
                println("entrei")
                openCalculatorActivity()
            } else {
                println("error")
                Toast.makeText(this, "Authentication failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLogin(): Boolean {
        if(binding.editTextEmailAddresSingIn.text.isEmpty()) {
            binding.editTextEmailAddresSingIn.error = "Email é um campo obrigatorio"
            return false
        }
        if(binding.editTextEmailAddresSingIn.text.isEmpty()) {
            binding.editTextEmailAddresSingIn.error = "Senha é um campo obrigatorio"
            return false
        }
        return true
    }

    private fun openCalculatorActivity() {
        val openCalculatorActivity = Intent(this, CalculatorUserBMI::class.java)
        startActivity(openCalculatorActivity)
    }
}