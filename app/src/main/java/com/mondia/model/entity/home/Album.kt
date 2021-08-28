package com.mondia.model.entity.home

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mondia.BR

class Album() : BaseObservable() , Parcelable {

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
    var albumImage: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumImage)
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
    var albumNumberOfTracks: Int=0
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumNumberOfTracks)
        }

    @get:Bindable
    var albumLabel: String=""
        set(value) {
            field = value
            notifyPropertyChanged(BR.albumLabel)
        }

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album {
            return Album(parcel)
        }

        override fun newArray(size: Int): Array<Album?> {
            return arrayOfNulls(size)
        }
    }

}
