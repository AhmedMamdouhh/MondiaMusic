package com.mondia.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mondia.manager.base.ResponseManager
import com.mondia.manager.connection.WebServiceCallback
import com.mondia.manager.utilities.Constants
import com.mondia.manager.utilities.Event
import com.mondia.model.entity.home.Album
import com.mondia.model.entity.home.Song
import com.mondia.repository.HomeRepository


class HomeViewModel : ViewModel(), WebServiceCallback {

    private val homeRepository = HomeRepository()
    private val _observeSongsData = MutableLiveData<Event<ArrayList<Song>>>()
    private val _observeAlbumsData = MutableLiveData<Event<ArrayList<Album>>>()
    private val _observeAlbumsClicked = MutableLiveData<Event<Boolean>>()
    private val _observeSongsClicked = MutableLiveData<Event<Boolean>>()

    init {
        ResponseManager.loading()
        homeRepository.getHomeData("aa", this)
    }

    //Click
    fun onAlbumsClicked(){

    }
    fun onSongsClicked(){

    }

    override fun onWebServiceSuccess(data: HashMap<String, ArrayList<*>>) {
        ResponseManager.hideLoading()

        _observeSongsData.value = Event(data[Constants.SONGS_LIST] as ArrayList<Song>)
        _observeAlbumsData.value = Event(data[Constants.ALBUMS_LIST] as ArrayList<Album>)
    }

    override fun onWebServiceError(error: String) {
        ResponseManager.hideLoading()
        if (error == Constants.EXCEPTION)
            ResponseManager.failed(error)
        else if (error == Constants.CONNECTION_ERROR)
            ResponseManager.noConnection()
    }

    //getters
    val observeSongsData: LiveData<Event<ArrayList<Song>>>
        get() = _observeSongsData
    val observeAlbumsData: LiveData<Event<ArrayList<Album>>>
        get() = _observeAlbumsData
    val observeAlbumsClicked: LiveData<Event<Boolean>>
        get() = _observeAlbumsClicked
    val observeSongsClicked: LiveData<Event<Boolean>>
        get() = _observeSongsClicked
}