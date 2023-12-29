package org.sopt.stopwatch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Timer
import kotlin.concurrent.timer

class MainViewModel : ViewModel() {
    private var timer: Timer? = null
    private var time = 0

    private val _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> = _isRunning

    private val _sec = mutableIntStateOf(0)
    val sec: State<Int> = _sec

    private val _mill = mutableIntStateOf(0)
    val mill: State<Int> = _mill

    private val _lapTimes = mutableStateOf(mutableListOf<String>())
    val lapTimes: State<List<String>> = _lapTimes

    private var lapCount = 1

    fun start() {
        timer = timer(period = 10) {
            time++
            _sec.intValue = time / 100
            _mill.intValue = time % 100
        }

        _isRunning.value = true
    }

    fun pause() {
        timer?.cancel()

        _isRunning.value = false
    }

    fun reset() {
        timer?.cancel()

        time = 0
        _sec.intValue = 0
        _mill.intValue = 0
        _lapTimes.value.clear()

        _isRunning.value = false
    }

    fun logLapTime() {
        _lapTimes.value.add("$lapCount LAP: ${sec.value}.${mill.value}")
        lapCount++
    }
}