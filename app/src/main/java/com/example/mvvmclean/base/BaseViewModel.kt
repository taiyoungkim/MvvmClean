package com.example.mvvmclean.base

import android.view.View
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.utils.ErrorType
import com.example.domain.utils.RemoteErrorEmitter
import com.example.mvvmclean.widget.utils.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), RemoteErrorEmitter {

    val mutableProgress = MutableStateFlow(View.GONE)
    val mutableScreenState = MutableStateFlow<ScreenState>(ScreenState.LOADING)
    val mutableErrorMessage = MutableSharedFlow<String>()
    val mutableSuccessMessage = MutableSharedFlow<String>()
    val mutableErrorType = MutableSharedFlow<ErrorType>()


    override fun onError(errorType: ErrorType) {
        viewModelScope.launch {
            mutableErrorType.emit(errorType)
        }
    }

    override fun onError(msg: String) {
        viewModelScope.launch {
            mutableErrorMessage.emit(msg)
        }
    }
}
