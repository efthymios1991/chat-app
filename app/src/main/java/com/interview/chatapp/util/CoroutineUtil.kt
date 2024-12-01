package com.interview.chatapp.util

import kotlinx.coroutines.CoroutineExceptionHandler

object CoroutineUtils {

    // Deals with exceptions that weren't dealt in the ViewModel.
    // To be used if there isn't an action/state that should take place on error.
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        /*
        if (!ExcludedExceptions.crashlyticsIgnoredExceptions.contains(exception.javaClass)) {
            //TODO share crashlytics
        }
         */
    }
}