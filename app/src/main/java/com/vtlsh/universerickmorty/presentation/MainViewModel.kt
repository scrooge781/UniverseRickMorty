package com.vtlsh.universerickmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtlsh.universerickmorty.business.ItemUseCase
import com.vtlsh.universerickmorty.business.NetworkUseCase
import com.vtlsh.universerickmorty.data.model.ItemRemote
import com.vtlsh.universerickmorty.data.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemLocalUseCase: ItemUseCase,
    private val networkUseCase: NetworkUseCase
): ViewModel() {

    private val _itemLocalList = MutableLiveData<List<Item>>()
    val itemLocalList: LiveData<List<Item>> get() = _itemLocalList

    private val _itemsRemoteData = MutableLiveData<ItemRemote>()
    val itemsRemoteData: LiveData<ItemRemote> get() = _itemsRemoteData


    fun getItemLocalList() {
        viewModelScope.launch {
            _itemLocalList.postValue(itemLocalUseCase.getItemList())
        }
    }

    fun getCharacter() {
        viewModelScope.launch {
            val result = networkUseCase.getCharacter().body()
            _itemsRemoteData.postValue(result!!)
        }
    }

}