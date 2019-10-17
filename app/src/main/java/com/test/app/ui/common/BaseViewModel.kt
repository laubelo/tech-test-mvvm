package com.test.app.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job = Job()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}