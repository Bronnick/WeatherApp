package com.example.weatherapp.app

import android.app.job.JobScheduler
import com.example.weatherapp.features.data.AppContainer
import kotlinx.coroutines.*


suspend fun main() = coroutineScope<Unit>{
    val job1 = launch {
        for(i in 0..2){
            println("iter $i")
            delay(500)
        }
    }

    val job2 = launch{
        job1.join()
        for(i in 0..2){
            println("iteration $i")
        }
    }
}