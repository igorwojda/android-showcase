package com.example.starter.presentation.listscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.starter.base.ext.asLiveData
import com.example.starter.base.lifedata.SingleLiveEvent
import com.example.starter.data.repository.CustomRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val customRepository: CustomRepository
) : ViewModel() {

    private val _navigate = SingleLiveEvent<NavDirections>()
    val navigate = _navigate.asLiveData()

    private val _data = MutableLiveData<List<String>>()
    val data = _data.asLiveData()

    fun onEnter() {
        viewModelScope.launch {
            val result = customRepository.getData()
            _data.postValue(result)
        }
    }

    fun onItemSelected(it: String) {
        val navDirections = ListFragmentDirections.actionListFragmentToDetailFragment(it)
        _navigate.postValue(navDirections)
    }
}
