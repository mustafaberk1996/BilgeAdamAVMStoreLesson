package com.example.avmstorelesson.ui.storeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avmstorelesson.Database
import com.example.avmstorelesson.data.model.Store
import com.example.avmstorelesson.data.state.StoreDeleteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoreDetailViewModel:ViewModel() {



    private val _deleteStoreState:MutableStateFlow<StoreDeleteState> = MutableStateFlow(StoreDeleteState.Idle)
    val deleteStoreState:StateFlow<StoreDeleteState> = _deleteStoreState


    fun deleteStore(store:Store){
        viewModelScope.launch {

            val index = Database.stores.indexOf(store)
            val isRemoved = Database.stores.remove(store)

            _deleteStoreState.value =
                if (isRemoved) StoreDeleteState.Success(store, index) else StoreDeleteState.Failed

        }
    }
}