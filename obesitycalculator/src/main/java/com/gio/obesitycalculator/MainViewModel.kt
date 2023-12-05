package com.gio.obesitycalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class MainViewModel : ViewModel() {
    private val _bmi = mutableStateOf(0.0)
    val bmi: MutableState<Double> = _bmi

    fun calculateBmi(height: Double, weight: Double) {
        _bmi.value = weight / (height / 100.0).pow(2)
    }
}