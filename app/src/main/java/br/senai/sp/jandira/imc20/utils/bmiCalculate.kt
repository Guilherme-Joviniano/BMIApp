package br.senai.sp.jandira.imc20.utils

import kotlin.math.pow

fun getBMI(weight: Float, height: Float) : Float {
    val result = weight / height.pow(2)
    return "%.2f".format(result).toFloat()
}

fun getStatusBmi(bmi: Float): String {
    if (bmi <= 18.5) {
        return "Abaixo do peso."
    }
    if (bmi >= 18.6 && bmi < 25) {
        return "Peso ideal."
    }
    if (bmi in 25.0..29.9) {
        return "Levemente acima do peso."
    }
    if (bmi in 30.0..34.9) {
        return "Obesidade grau I."
    }
    if (bmi in 35.0..39.9) {
        return "Obesidade grau II (severa)."
    }
    return "Obesidade III (mórbida)."
}