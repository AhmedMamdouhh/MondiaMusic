package com.mondia.model.entity.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mondia.BR

class Album : BaseObservable() {

    @get:Bindable
    var albumID: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumID)
        }

    @get:Bindable
    var albumType: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumType)
        }

    @get:Bindable
    var albumPublishingDate: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumPublishingDate)
        }

    @get:Bindable
    var albumTitle: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumTitle)
        }

    @get:Bindable
    var albumDuration: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumDuration)
        }

    @get:Bindable
    var albumArtist: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumArtist)
        }

    @get:Bindable
    var numberOfTracks: Int=0
        set(value) {
            field = value
            notifyPropertyChanged(BR.numberOfTracks)
        }

}
