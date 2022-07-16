package com.example.mvvmclean.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.model.GithubResponse
import com.example.domain.usecase.GetUserRepoUseCase
import com.example.mvvmclean.base.BaseViewModel
import com.example.mvvmclean.widget.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserRepoUseCase: GetUserRepoUseCase
) : BaseViewModel() {
    private val _flowUserRepo = MutableSharedFlow<List<GithubResponse>>()
    val flowUserRepo = _flowUserRepo.asSharedFlow()

    fun getUserRepo(owner: String) = viewModelScope.launch {
        val response = getUserRepoUseCase.execute(this@MainViewModel, owner)
        if (response == null)
            mutableScreenState.emit(ScreenState.ERROR)
        else
            response.let { _flowUserRepo.emit(it)}
    }
}