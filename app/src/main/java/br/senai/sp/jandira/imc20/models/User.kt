package br.senai.sp.jandira.imc20.models

import kotlin.math.pow

data class User (
    var id : Long  = 0,
    var name  : String,
    var email : String,
    var password : String,
    var weight : Double,
    var height : Double,
)