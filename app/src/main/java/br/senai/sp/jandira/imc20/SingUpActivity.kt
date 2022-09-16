package br.senai.sp.jandira.imc20

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.senai.sp.jandira.imc20.databinding.ActivitySingUpBinding
import br.senai.sp.jandira.imc20.models.User
import java.util.concurrent.atomic.AtomicLong

class SingUpActivity : AppCompatActivity() {

    private val counter: AtomicLong = AtomicLong()
    private lateinit var binding: ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_sing_up)

        binding.buttonSingUp.setOnClickListener {
            saveUser()
        }
    }

    private fun  saveUser() {
        val user = User(
            id = counter.incrementAndGet(),
            name = binding.editTextName.text.toString(),
            email = binding.editTextEmail.text.toString(),
            password = binding.editTextPassword.text.toString(),
            weight = binding.editTextWeight.text.toString().toDouble(),
            height = binding.editTextHeight.text.toString().toDouble(),
        );

        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        val editDados = dados.edit();

        // Inserir dados do user no shared preferences

        editDados.putLong("id", user.id);
        editDados.putString("name", user.name)
        editDados.putString("email", user.email)
        editDados.putString("password", user.password)
        editDados.putFloat("weight", user.weight.toFloat())
        editDados.putFloat("height", user.height.toFloat())

        if(editDados.commit()) {
            Toast.makeText(this, "Usuario Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Ocorreu um erro no cadastro", Toast.LENGTH_SHORT).show()
        }
    }
}