package com.example.mvvmclean.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.GithubResponse
import com.example.domain.usecase.GetUserRepoUseCase
import com.example.mvvmclean.base.BaseViewModel
import com.example.mvvmclean.widget.utils.ScreenState
import com.example.mvvmclean.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserRepoUseCase: GetUserRepoUseCase
) : BaseViewModel() {
    val eventUserRepo: LiveData<List<GithubResponse>> get() = _eventUserRepo
    private val _eventUserRepo = SingleLiveEvent<List<GithubResponse>>()

    fun getUserRepo(owner: String) = viewModelScope.launch {
        val response = getUserRepoUseCase.execute(this@MainViewModel, owner)
        if (response == null)
            mutableScreenState.postValue(ScreenState.ERROR)
        else
            response.let { _eventUserRepo.postValue(it) }
    }
}