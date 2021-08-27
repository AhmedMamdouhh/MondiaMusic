package com.mondia.ui.home.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mondia.databinding.ItemSongBinding
import com.mondia.model.entity.home.Song
import com.mondia.ui.home.HomeViewModel
import com.mondia.ui.home.albums.AlbumsViewHolder

class SongsAdapter(
    private val songsList: ArrayList<Song>,
    private val homeViewModel: HomeViewModel
):RecyclerView.Adapter<SongsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder =
        SongsViewHolder(ItemSongBinding.inflate(LayoutInflater.from(parent.context),parent,false),homeViewModel)


    override fun onBindViewHolder(holderResult: SongsViewHolder, position: Int) {
        holderResult.bind(songsList[position])

    }

    override fun getItemCount(): Int {
        return songsList.size
    }
}