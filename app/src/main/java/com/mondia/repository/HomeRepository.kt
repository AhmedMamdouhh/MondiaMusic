package com.mondia.repository

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.mondia.manager.connection.ApiEndPoints
import com.mondia.manager.connection.HttpUrlConnectionManager
import com.mondia.manager.connection.WebServiceCallback
import com.mondia.manager.utilities.Constants
import com.mondia.model.entity.home.Album
import com.mondia.model.entity.home.Song
import org.json.JSONArray
import java.net.URL
import java.net.URLEncoder


class HomeRepository {

    private val songsList = ArrayList<Song>()
    private val albumsList = ArrayList<Album>()
    private var tracksMap: HashMap<String, ArrayList<*>> = HashMap()
    private var responseResult: String = ""
    private var errorMessage: String = ""

    fun getHomeData(query: String, callback: WebServiceCallback, token: String) {

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                if (tracksMap.size == 2 && errorMessage == "") {
                    callback.onWebServiceSuccess(tracksMap)
                } else
                    callback.onWebServiceError(errorMessage)
            }
        }

        val thread: Thread = object : Thread() {
            override fun run() {
                try {

                    errorMessage = ""

                    val stringBuilder = StringBuilder(ApiEndPoints.DATA_GROUPED)
                    stringBuilder.append(ApiEndPoints.QUERY)
                    stringBuilder.append(URLEncoder.encode(query, Constants.UTF))

                    responseResult =
                        HttpUrlConnectionManager.getHttpInstance(
                            URL(stringBuilder.toString()), Constants.GET, token
                        )

                } finally {
                    try {
                        if (responseResult == Constants.EXCEPTION || responseResult.isEmpty())
                            errorMessage = Constants.EXCEPTION
                        else if (responseResult == Constants.CONNECTION_ERROR)
                            errorMessage = Constants.CONNECTION_ERROR
                        else {

                            if (responseResult == "[]" || responseResult.isEmpty()) {
                                errorMessage = "Nothing found .. try another songs"
                            } else {
                                reset()
                                getSongsJsonList(responseResult)
                                getAlbumsJsonList(responseResult)
                            }
                        }
                    } finally {
                        handler.sendEmptyMessage(0)
                    }
                }
            }
        }
        thread.start()

    }

    private fun getSongsJsonList(fullJson: String) {
        val jsonFullArray = JSONArray(fullJson)

        for (i in 0 until jsonFullArray.length()) {
            val checkForTracks = jsonFullArray.getJSONObject(i)
            if (checkForTracks.get(Constants.GROUP_NAME) == Constants.Track) {
                val songsJsonArray = checkForTracks.getJSONArray(Constants.SONGS_JSON_ARRAY_KEY)

                for (index in 0 until songsJsonArray.length()) {
                    val item = songsJsonArray.getJSONObject(index)
                    val songsArtistObject =
                        songsJsonArray.getJSONObject(index)
                            .getJSONObject(Constants.SONGS_ARTIST_KEY)
                    val songsImageObject =
                        songsJsonArray.getJSONObject(index).getJSONObject(Constants.SONGS_IMAGE_KEY)

                    val song = Song()
                    song.songID = item.get(Constants.TRACKS_ID) as Int
                    song.songTitle = item.get(Constants.TRACKS_TITLE) as String
                    song.songDuration = item.get(Constants.TRACKS_DURATION) as Int
                    song.songType = item.get(Constants.TRACKS_TYPE) as String
                    song.songArtist = songsArtistObject.get(Constants.TRACKS_ARTIST_NAME).toString()
                    song.songImage = songsImageObject.get(Constants.TRACKS_IMAGE_DEFAULT).toString()

                    songsList.add(song)
                    tracksMap[Constants.SONGS_LIST] = songsList

                }

            }
        }
    }

    private fun getAlbumsJsonList(fullJson: String) {
        val jsonFullArray = JSONArray(fullJson)

        for (i in 0 until jsonFullArray.length()) {
            val checkForAlbums = jsonFullArray.getJSONObject(i)
            if (checkForAlbums.get(Constants.GROUP_NAME) == Constants.ALBUM) {
                val albumsJsonArray = checkForAlbums.getJSONArray(Constants.ALBUMS_JSON_ARRAY_KEY)

                for (index in 0 until albumsJsonArray.length()) {
                    val item = albumsJsonArray.getJSONObject(index)
                    val albumsArtistObject =
                        albumsJsonArray.getJSONObject(index)
                            .getJSONObject(Constants.ALBUMS_ARTIST_KEY)
                    val albumsImageObject =
                        albumsJsonArray.getJSONObject(index)
                            .getJSONObject(Constants.ALBUMS_IMAGE_KEY)


                    val album = Album()
                    album.albumID = item.get(Constants.TRACKS_ID) as Int
                    album.albumTitle = item.get(Constants.TRACKS_TITLE) as String
                    album.albumDuration = item.get(Constants.TRACKS_DURATION) as Int
                    album.albumType = item.get(Constants.TRACKS_TYPE) as String
                    album.albumLabel = item.get(Constants.ALBUMS_LABEL_KEY) as String
                    album.albumNumberOfTracks =
                        item.get(Constants.ALBUMS_NUMBER_OF_TRACKS_KEY) as Int
                    album.albumPublishingDate = item.get(Constants.TRACKS_PUBLISHING_DATE) as String
                    album.albumArtist =
                        albumsArtistObject.get(Constants.TRACKS_ARTIST_NAME).toString()
                    album.albumImage =
                        albumsImageObject.get(Constants.TRACKS_IMAGE_DEFAULT).toString()

                    albumsList.add(album)
                    tracksMap[Constants.ALBUMS_LIST] = albumsList

                }

            }
        }
    }

    private fun reset() {
        tracksMap.clear()
        songsList.clear()
        albumsList.clear()
    }

}