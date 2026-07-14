package com.aks.parkingapp.services

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class SyncWorker(
    context: Context,
    workerParameters: WorkerParameters):
    CoroutineWorker(context,workerParameters) {

    override suspend fun doWork(): Result {

        Log.d("WorkManager", "API Calling")
        delay(5000.milliseconds)
        // Api call
        return Result.success()
    }

}