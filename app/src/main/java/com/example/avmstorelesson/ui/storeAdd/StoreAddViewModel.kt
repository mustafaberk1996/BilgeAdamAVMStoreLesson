package com.example.avmstorelesson.ui.storeAdd

import android.provider.ContactsContract.Data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avmstorelesson.Database
import com.example.avmstorelesson.data.model.Photo
import com.example.avmstorelesson.data.model.Store
import com.example.avmstorelesson.data.state.AdapterState
import com.example.avmstorelesson.data.state.PhotoListState
import com.example.avmstorelesson.data.state.StoreAddState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoreAddViewModel:ViewModel() {


    private val _photoListState:MutableStateFlow<PhotoListState> = MutableStateFlow(PhotoListState.Idle)
    val photoListState:StateFlow<PhotoListState> = _photoListState

    private val _adapterState: MutableSharedFlow<AdapterState> = MutableSharedFlow()
    val adapterState: SharedFlow<AdapterState> = _adapterState

    private val _selectedPhoto:MutableStateFlow<Photo?> = MutableStateFlow(null)

    private val _storeAddState:MutableStateFlow<StoreAddState> = MutableStateFlow(StoreAddState.Idle)
    val storeAddState:StateFlow<StoreAddState> = _storeAddState


    init {
        viewModelScope.launch {
            _photoListState.value = PhotoListState.Result(Database.photos)
        }
    }

    fun photoSelected(clickedPhoto: Photo) {
        viewModelScope.launch {

            //once secilen bir photo var ise onu false yap ve adapter statei guncelle
            _selectedPhoto.value?.let {
                val index = (_photoListState.value as PhotoListState.Result).photos.indexOf(it)
                (_photoListState.value as PhotoListState.Result).photos[index].isSelected = false
                _adapterState.emit(AdapterState.Changed(index))
            }

            //tikladigim resmi viewmodel icerisindeki bir degiskende tut
            _selectedPhoto.value = clickedPhoto

            //listeye ulasip tikladigim resmi secili yap
            if (_photoListState.value is PhotoListState.Result) {
                val index =
                    (_photoListState.value as PhotoListState.Result).photos.indexOf(clickedPhoto)
                (_photoListState.value as PhotoListState.Result).photos[index].isSelected = true

                _adapterState.emit(AdapterState.Changed(index))
            }
        }


    }

    fun saveStore(name: String, floor: String, block: String, description: String) {
        viewModelScope.launch {
                _selectedPhoto.value?.let {
                    val store = Store(1, name, it.imageUrl, floor, block, description)
                    Database.stores.add(store)
                    //TODO burada bir sorun var haaa
//                    _selectedPhoto.value = null

                    _storeAddState.emit(StoreAddState.Added(Database.stores.lastIndex))
                }?: kotlin.run {
                    //hata mesaji yada uyari mesaji ver....
                }

        }
    }


}