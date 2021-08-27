package com.mondia.model.entity.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mondia.BR

class Song : BaseObservable() {

    @get:Bindable
    var songID: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.songID)
        }

    @get:Bindable
    var songType: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.songType)
        }

    @get:Bindable
    var songTitle: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.songTitle)
        }

    @get:Bindable
    var songDuration: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.songDuration)
        }

    @get:Bindable
    var songArtist: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.songArtist)
        }



}
