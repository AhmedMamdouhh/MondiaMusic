package com.mondia.ui.home

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mondia.manager.base.ResponseManager
import com.mondia.manager.connection.WebServiceCallback
import com.mondia.manager.utilities.Constants
import com.mondia.manager.utilities.Event
import com.mondia.manager.utilities.SharedPreferencesManager
import com.mondia.model.entity.home.Album
import com.mondia.model.entity.home.Song
import com.mondia.repository.HomeRepository


class HomeViewModel(
    private val sharedPreferences: SharedPreferencesManager,
    private val homeRepository: HomeRepository,
    token: String
) : ViewModel(), WebServiceCallback {

    private val _observeSongsData = MutableLiveData<Event<ArrayList<Song>>>()
    private val _observeAlbumsData = MutableLiveData<Event<ArrayList<Album>>>()
    private val _observeAlbumsClicked = MutableLiveData<Event<Album>>()
    private val _observeSongsClicked = MutableLiveData<Event<Song>>()

    init {
        sharedPreferences.savePlaceObj(token,Constants.TOKEN)
        callRepository("zz")
    }

    private fun callRepository(query:String){
        ResponseManager.loading()
        homeRepository.getHomeData(query, this,sharedPreferences.getSavedData(Constants.TOKEN)!!)
    }


    //Click
    fun onAlbumsClicked(album: Album){ _observeAlbumsClicked.value = Event(album) }
    fun onSongsClicked(song:Song){ _observeSongsClicked.value = Event(song) }
    fun onSearchClicked(query: EditText){
        if(validateQuery(query.text.toString()))
            callRepository(query.text.toString())
    }


    override fun onWebServiceSuccess(data: HashMap<String, ArrayList<*>>) {
        ResponseManager.hideLoading()

        val song:ArrayList<Song>
        song = data[Constants.SONGS_LIST] as ArrayList<Song>

        val album:ArrayList<Album>
        album = data[Constants.ALBUMS_LIST] as ArrayList<Album>

        Log.e("checkForAlbums", "onWebServiceSuccess: ${song[1].songTitle} --- ${album[2].albumTitle}" )

        _observeSongsData.value = Event(data[Constants.SONGS_LIST] as ArrayList<Song>)
        _observeAlbumsData.value = Event(data[Constants.ALBUMS_LIST] as ArrayList<Album>)
    }

    override fun onWebServiceError(error: String) {
        ResponseManager.hideLoading()
         if (error == Constants.CONNECTION_ERROR)
            ResponseManager.noConnection()
        else
            ResponseManager.failed(error)
    }


    //getters
    val observeSongsData: LiveData<Event<ArrayList<Song>>>
        get() = _observeSongsData
    val observeAlbumsData: LiveData<Event<ArrayList<Album>>>
        get() = _observeAlbumsData
    val observeAlbumsClicked: LiveData<Event<Album>>
        get() = _observeAlbumsClicked
    val observeSongsClicked: LiveData<Event<Song>>
        get() = _observeSongsClicked


    //Validation
    private fun validateQuery(query: String): Boolean {
        if(query.length<2 || query.isEmpty()) {
            ResponseManager.failed("length must be at least 2 characters long")
            return false
        }
        return true
    }

}