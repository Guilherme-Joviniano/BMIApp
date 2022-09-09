package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateUserBmiBinding

class CalculatorUserBMI : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateUserBmiBinding
    private var weightForm = 0F;
    private var  heightForm = 0F;
    private fun getProfile() {
        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        val name = dados.getString("name", "")
        val email = dados.getString("email", "")
        val weight = dados.getFloat("weight", 0F).toString()
        val height = dados.getFloat("height", 0F).toString()

        binding.textViewName.text = name.toString()
        binding.textViewEmail.text = email.toString()


        binding.textViewWeight.text = "Peso: $weight"
        binding.textViewHeight.text = "Altura: $height"



    }



    private fun updateData() {
        val editor = getSharedPreferences("dados", MODE_PRIVATE);

        val editDados = editor.edit()

        // UPDATE THE SHARED PREFERENCES
        editDados.putFloat("weight", this.weightForm)
        editDados.putFloat("height", this.heightForm)

        editDados.apply()

    }
    private fun validateFormData(): Boolean {
        if (binding.editTextHeightInput.text.isEmpty()) {
            binding.editTextHeightInput.error = "Campo obrigatorio"
            return false
        }
        if (binding.editTextWeightInput.text.isEmpty()) {
            binding.editTextWeightInput.error = "Campo obrigatorio"
            return false
        }
        return true
    }
    private fun getFormData() {
        if(validateFormData()) {
            this.weightForm = binding.editTextWeightInput.text.toString().toFloat()
            this.heightForm = binding.editTextHeightInput.text.toString().toFloat()
        }
    }
    private fun openResultActivity()  {
        val openResultActivity = Intent(this, ResultActivity::class.java)

        openResultActivity.putExtra("weight", this.weightForm)
        openResultActivity.putExtra("height", this.heightForm)

        startActivity(openResultActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculateUserBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        getProfile()

        binding.buttonCalculate.setOnClickListener {
            getFormData();

            updateData();

            if (validateFormData()) openResultActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        getProfile()
    }
}