package com.example.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.data.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()

    val image = repository.image

    private val _loading = MutableLiveData<Boolean>(false)
    val  loading : LiveData<Boolean>
    get() = _loading

    fun loadData(){
        viewModelScope.launch {
            _loading.value = true
            repository.loadingImage()
            _loading.value = false
        }
    }
    // TODO
}
