package com.interview.chatapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun ViewModel.vmLaunch(
    context: CoroutineContext = CoroutineUtils.exceptionHandler,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context) { block.invoke(this) }
