package com.mondia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mondia.databinding.FragmentHomeBinding
import com.mondia.manager.utilities.EventObserver
import com.mondia.manager.utilities.recyclerAnimationExtension
import com.mondia.ui.home.albums.AlbumsAdapter
import com.mondia.ui.home.songs.SongsAdapter

class HomeFragment:Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    private  var homeViewModel = HomeViewModel()
    private lateinit var albumsAdapter: AlbumsAdapter
    private lateinit var songsAdapter: SongsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeBinding.homeListener = homeViewModel


        observeSongs()
        observeAlbums()

        return homeBinding.root
    }

    private fun observeSongs() {

        homeViewModel.observeSongsData.observe(viewLifecycleOwner,EventObserver{
            songsAdapter = SongsAdapter(it,homeViewModel)
            homeBinding.rvHomeSongsList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            homeBinding.rvHomeSongsList.adapter = songsAdapter
            recyclerAnimationExtension(context,homeBinding.rvHomeSongsList)

        })
    }

    private fun observeAlbums() {
        homeViewModel.observeAlbumsData.observe(viewLifecycleOwner,EventObserver{
            albumsAdapter = AlbumsAdapter(it,homeViewModel)
            homeBinding.rvHomeAlbumsList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            homeBinding.rvHomeAlbumsList.adapter = albumsAdapter
            recyclerAnimationExtension(context,homeBinding.rvHomeAlbumsList)
        })
    }


}