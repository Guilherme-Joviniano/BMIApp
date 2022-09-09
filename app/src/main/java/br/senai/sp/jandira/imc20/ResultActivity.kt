package br.senai.sp.jandira.imc20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc20.databinding.ActivityResultBinding
import br.senai.sp.jandira.imc20.utils.getBMI
import br.senai.sp.jandira.imc20.utils.getStatusBmi

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide();

        val weight = intent.getFloatExtra("weight", 0F)
        val height = intent.getFloatExtra("height", 0F)

        var bmi = getBMI(weight, height)
        var status = getStatusBmi(bmi);

        binding.textViewResult.text = "$bmi"
        binding.textViewStatus.text = "$status"

    }
}