package com.aditya.github.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GetUserNameViewModel : ViewModel() {
    val isClicked = MutableLiveData<Boolean>()

    fun onClick() {
        isClicked.value = true
    }
}