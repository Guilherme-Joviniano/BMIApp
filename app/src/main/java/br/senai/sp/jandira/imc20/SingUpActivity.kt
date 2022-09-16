package br.senai.sp.jandira.imc20

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.senai.sp.jandira.imc20.models.User
import java.util.concurrent.atomic.AtomicLong

class SingUpActivity : AppCompatActivity() {

    private val counter: AtomicLong = AtomicLong()

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var editWeight: EditText
    lateinit var editHeight: EditText
    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_sing_up)

        editName = findViewById(R.id.editTextName)
        editEmail = findViewById(R.id.editTextEmail)
        editPassword = findViewById(R.id.editTextPassword)
        editWeight = findViewById(R.id.editTextWeight)
        editHeight = findViewById(R.id.editTextHeight)
        buttonSave = findViewById(R.id.buttonSingUp);

        buttonSave.setOnClickListener {
            saveUser()
        }
    }

    private fun  saveUser() {
        val user = User(
            id = counter.incrementAndGet(),
            name = editName.text.toString(),
            email = editEmail.text.toString(),
            password = editPassword.text.toString(),
            weight = editWeight.text.toString().toDouble(),
            height = editHeight.text.toString().toDouble(),
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