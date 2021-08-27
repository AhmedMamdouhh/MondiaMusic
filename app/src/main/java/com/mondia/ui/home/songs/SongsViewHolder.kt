package com.mondia.ui.home.songs

import androidx.recyclerview.widget.RecyclerView
import com.mondia.databinding.ItemSongBinding
import com.mondia.model.entity.home.Song
import com.mondia.ui.home.HomeViewModel

class SongsViewHolder(
    private val binding: ItemSongBinding,
    private val homeViewModel: HomeViewModel
):RecyclerView.ViewHolder(binding.root) {

    fun bind(song:Song){
        binding.songObject = song
        binding.songListener = homeViewModel
    }
}