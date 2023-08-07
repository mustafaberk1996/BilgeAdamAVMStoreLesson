package com.example.avmstorelesson.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avmstorelesson.Database
import com.example.avmstorelesson.data.model.Store
import com.example.avmstorelesson.data.state.AdapterState
import com.example.avmstorelesson.data.state.StoreListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {




    private val _storeListState:MutableStateFlow<StoreListState> = MutableStateFlow(StoreListState.Idle)
    val storeListState:StateFlow<StoreListState> = _storeListState

    private val _adapterState:MutableSharedFlow<AdapterState> = MutableSharedFlow()
    val adapterState:SharedFlow<AdapterState> = _adapterState


    init {
        getStores()
    }

    private fun getStores() {
        viewModelScope.launch {
            _storeListState.value = StoreListState.Loading
            delay(1000)
            _storeListState.value = StoreListState.Result(Database.stores)
        }
    }

    fun deleteStore(index: Int) {
        viewModelScope.launch {
            ///val index = Database.stores.indexOf(store)

            _adapterState.emit(AdapterState.Removed(index))

        }
    }

}