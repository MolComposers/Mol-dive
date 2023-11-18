package com.gio.molcomposer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite
    fun reverseIsFavorite() {
        _isFavorite.value = !_isFavorite.value
    }
}