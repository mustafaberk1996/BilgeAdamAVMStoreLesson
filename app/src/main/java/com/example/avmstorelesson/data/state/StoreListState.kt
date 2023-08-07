package com.example.avmstorelesson.data.state

import com.example.avmstorelesson.data.model.Store

sealed class StoreListState{
    object Idle : StoreListState()
    object Loading : StoreListState()
    class Result(val stores: List<Store>) : StoreListState()
    class Error(val throwable: Throwable? = null) : StoreListState()
}
