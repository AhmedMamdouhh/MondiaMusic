package com.mondia.ui.home.albums

import androidx.recyclerview.widget.RecyclerView
import com.mondia.databinding.ItemAlbumBinding
import com.mondia.databinding.ItemSongBinding
import com.mondia.model.entity.home.Album
import com.mondia.model.entity.home.Song
import com.mondia.ui.home.HomeViewModel

class AlbumsViewHolder(
    private val binding: ItemAlbumBinding,
    private val homeViewModel: HomeViewModel
):RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album){
        binding.albumsObject = album
        binding.albumsListener = homeViewModel
    }
}