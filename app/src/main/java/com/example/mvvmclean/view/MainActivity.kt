package com.example.mvvmclean.view

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmclean.R
import com.example.mvvmclean.base.BaseActivity
import com.example.mvvmclean.databinding.ActivityMainBinding
import com.example.mvvmclean.viewmodel.MainViewModel
import com.example.mvvmclean.widget.utils.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun init() {
        binding.activity = this
        observeViewModel()
    }

    fun clickSearchBtn(view: View){
        mainViewModel.getUserRepo(binding.githubNameEditTxt.text.toString())
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.mutableScreenState.collect {
                when (it) {
                    ScreenState.RENDER -> shortShowToast("성공!")
                    ScreenState.ERROR -> shortShowToast("에러 발생!!")
                    ScreenState.LOADING -> mainViewModel.mutableProgress.emit(View.VISIBLE)
                    else -> shortShowToast("알수없는 에러 발생!!")
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.flowUserRepo.collectLatest {
                it.map { item ->
                    binding.responseTxt.text = item.url
                }
            }
        }
    }
}