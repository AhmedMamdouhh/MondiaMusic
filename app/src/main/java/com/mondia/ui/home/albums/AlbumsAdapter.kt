package com.mondia.ui.home.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mondia.databinding.ItemAlbumBinding
import com.mondia.databinding.ItemSongBinding
import com.mondia.model.entity.home.Album
import com.mondia.model.entity.home.Song
import com.mondia.ui.home.HomeViewModel

class AlbumsAdapter(
    private val albumsList: ArrayList<Album>,
    private val homeViewModel: HomeViewModel
):RecyclerView.Adapter<AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder =
        AlbumsViewHolder(ItemAlbumBinding.inflate(LayoutInflater.from(parent.context),parent,false),homeViewModel)


    override fun onBindViewHolder(holderResult: AlbumsViewHolder, position: Int) {
        holderResult.bind(albumsList[position])

    }

    override fun getItemCount(): Int {
        return albumsList.size
    }
}