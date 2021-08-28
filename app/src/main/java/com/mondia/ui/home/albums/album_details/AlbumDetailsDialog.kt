package com.mondia.ui.home.albums.album_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mondia.databinding.DialogAlbumDetailsBinding
import com.mondia.manager.base.BaseBottomSheet
import com.mondia.manager.utilities.Constants

class AlbumDetailsDialog:BaseBottomSheet() {

    private lateinit var albumDetailsBinding: DialogAlbumDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        albumDetailsBinding = DialogAlbumDetailsBinding.inflate(inflater,container,false)

        getBundleMessage()

        return albumDetailsBinding.root
    }

    private fun getBundleMessage() {
        if (arguments?.containsKey(Constants.BUNDLE_ALBUM) == true) {
            albumDetailsBinding.albumObject =  requireArguments().getParcelable(Constants.BUNDLE_ALBUM)
        }
    }
}