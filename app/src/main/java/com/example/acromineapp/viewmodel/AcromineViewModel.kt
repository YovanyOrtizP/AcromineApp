package com.example.acromineapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acromineapp.data.model.AcromineResponse
import com.example.acromineapp.data.repository.AcromineRepository
import com.example.acromineapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcromineViewModel @Inject constructor(
    private val acromineRepository: AcromineRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel(){

    //Backing properties
    private val _resultSF : MutableLiveData<UIState<List<AcromineResponse>>> = MutableLiveData(UIState.LOADING)
    val resultSF: LiveData<UIState<List<AcromineResponse>>> get() = _resultSF

    fun flowGetAcromineFlow(sf: String){
        //launch the coroutine meanwhile the viewModel is alive
        viewModelScope.launch (ioDispatcher) {
            acromineRepository.getAcromineFlow(sf).collect(){
                _resultSF.postValue(it)
            }
        }
    }
}
