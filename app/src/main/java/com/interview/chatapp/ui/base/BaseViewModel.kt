package com.interview.chatapp.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S : UiState, A : UiAction> : ViewModel() {

    abstract val initialState: S

    private lateinit var _state: MutableStateFlow<S>

    val uiState: StateFlow<S>
        get() {
            if (!::_state.isInitialized) {
                _state = MutableStateFlow(initialState)

            }
            return _state
        }

    open fun getAction(action: A, previousState: S) { }

    fun postAction(action: A) {
        getAction(action, _state.value)
    }

    fun updateViewState(newState: S) {
        _state.tryEmit(newState)
    }
}