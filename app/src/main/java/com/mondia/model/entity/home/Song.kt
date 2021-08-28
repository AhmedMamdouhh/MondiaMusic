package com.mondia.model.entity.home

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mondia.BR

class Song() : BaseObservable(),Parcelable {

    @get:Bindable
    var songID: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.songID)
        }

    @get:Bindable
    var songImage: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.songImage)
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

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }


}
