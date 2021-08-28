package com.mondia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mondia.databinding.FragmentHomeBinding
import com.mondia.manager.utilities.Constants
import com.mondia.manager.utilities.EventObserver
import com.mondia.manager.utilities.SharedPreferencesManager
import com.mondia.manager.utilities.recyclerAnimationExtension
import com.mondia.repository.HomeRepository
import com.mondia.ui.home.albums.AlbumsAdapter
import com.mondia.ui.home.albums.album_details.AlbumDetailsDialog
import com.mondia.ui.home.songs.SongsAdapter
import com.mondia.ui.home.songs.song_details.SongDetailsDialog

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    private lateinit var homeViewModel : HomeViewModel
    private lateinit var albumsAdapter: AlbumsAdapter
    private lateinit var songsAdapter: SongsAdapter
    private var token:String=""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getBundleToken()
        initializeViewModel()

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeBinding.homeListener = homeViewModel


        observeSongs()
        observeSongsClicked()
        observeAlbums()
        observeAlbumsClicked()

        return homeBinding.root
    }

    private fun initializeViewModel() {
        homeViewModel=ViewModelProvider(requireActivity(),HomeViewModelFactory
            (SharedPreferencesManager.getInstance(requireContext()),
            HomeRepository(),
            token
        ))
            .get(HomeViewModel::class.java)    }

    private fun getBundleToken() {
        if (arguments?.containsKey(Constants.TOKEN) == true) {
            token = requireArguments().getString(Constants.TOKEN)!!
        }
    }

    private fun observeSongs() {
        homeViewModel.observeSongsData.observe(viewLifecycleOwner, EventObserver {

            homeBinding.motionHomeParent.visibility = VISIBLE

            songsAdapter = SongsAdapter(it, homeViewModel)
            homeBinding.rvHomeSongsList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            homeBinding.rvHomeSongsList.adapter = songsAdapter
            recyclerAnimationExtension(context, homeBinding.rvHomeSongsList)
        })
    }

    private fun observeSongsClicked() {
        homeViewModel.observeSongsClicked.observe(viewLifecycleOwner, EventObserver {
            val songDetailsDialog = SongDetailsDialog()
            val bundle = Bundle()
            bundle.putParcelable(Constants.BUNDLE_SONG, it)
            songDetailsDialog.arguments = bundle
            songDetailsDialog.show(
                requireActivity().supportFragmentManager,
                Constants.Song_DETAILS_SHEET
            )
        })
    }

    private fun observeAlbums() {
        homeViewModel.observeAlbumsData.observe(viewLifecycleOwner, EventObserver {
            albumsAdapter = AlbumsAdapter(it, homeViewModel)
            homeBinding.rvHomeAlbumsList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            homeBinding.rvHomeAlbumsList.adapter = albumsAdapter
            recyclerAnimationExtension(context, homeBinding.rvHomeAlbumsList)
        })
    }

    private fun observeAlbumsClicked() {
        homeViewModel.observeAlbumsClicked.observe(viewLifecycleOwner, EventObserver {
            val albumDetailsDialog = AlbumDetailsDialog()
            val bundle = Bundle()
            bundle.putParcelable(Constants.BUNDLE_ALBUM, it)
            albumDetailsDialog.arguments = bundle
            albumDetailsDialog.show(
                requireActivity().supportFragmentManager,
                Constants.ALBUM_DETAILS_SHEET
            )

        })
    }


}