package com.mondia.ui.home.songs.song_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mondia.databinding.DialogSongDetailsBinding
import com.mondia.manager.base.BaseBottomSheet
import com.mondia.manager.utilities.Constants

class SongDetailsDialog:BaseBottomSheet() {

    private lateinit var songDetailsBinding: DialogSongDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        songDetailsBinding = DialogSongDetailsBinding.inflate(inflater,container,false)
        getBundleMessage()

        return songDetailsBinding.root
    }


    private fun getBundleMessage() {
        if (arguments?.containsKey(Constants.BUNDLE_SONG) == true) {
            songDetailsBinding.songObject =  requireArguments().getParcelable(Constants.BUNDLE_SONG)
        }
    }
}