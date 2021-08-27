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

    fun getHomeData(query: String, callback: WebServiceCallback) {

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                if (tracksMap.size > 0)
                    callback.onWebServiceSuccess(tracksMap)
                else
                    callback.onWebServiceError(errorMessage)
            }
        }

        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    val stringBuilder = StringBuilder(ApiEndPoints.DATA_GROUPED)
                    stringBuilder.append(ApiEndPoints.QUERY)
                    stringBuilder.append(URLEncoder.encode(query, Constants.UTF))

                    responseResult =
                        HttpUrlConnectionManager.getHttpInstance(URL(stringBuilder.toString()))

                    if (responseResult == Constants.EXCEPTION)
                        errorMessage = Constants.EXCEPTION
                    else if (responseResult == Constants.CONNECTION_ERROR)
                        errorMessage = Constants.CONNECTION_ERROR
                    else {
                        getSongsJsonList(responseResult)
                        getAlbumsJsonList(responseResult)
                    }

                } finally {
                    handler.sendEmptyMessage(0)
                }
            }
        }
        thread.start()

    }

    fun getSongsJsonList(fullJson: String) {
        val jsonFullArray = JSONArray(fullJson)
        val songsJsonObject = jsonFullArray.getJSONObject(Constants.SONGS_INDEX)
        val songsJsonArray = songsJsonObject.getJSONArray(Constants.SONGS_JSON_ARRAY_KEY)


        for (index in 0 until songsJsonArray.length()) {
            val item = songsJsonArray.getJSONObject(index)
            val songsArtistObject =
                songsJsonArray.getJSONObject(index).getJSONObject(Constants.SONGS_ARTIST_KEY)

            val song = Song()
            song.songID = item.get(Constants.TRACKS_ID) as Int
            song.songTitle = item.get(Constants.TRACKS_TITLE) as String
            song.songDuration = item.get(Constants.TRACKS_DURATION) as Int
            song.songType = item.get(Constants.TRACKS_TYPE) as String
            song.songArtist = songsArtistObject.get(Constants.TRACKS_ARTIST_NAME).toString()

            songsList.add(song)
            tracksMap[Constants.SONGS_LIST] = songsList

        }
    }

    fun getAlbumsJsonList(fullJson: String) {
        val jsonFullArray = JSONArray(fullJson)
        val albumsJsonObject = jsonFullArray.getJSONObject(Constants.ALBUMS_INDEX)
        val albumsJsonArray = albumsJsonObject.getJSONArray(Constants.ALBUMS_JSON_ARRAY_KEY)


        for (index in 0 until albumsJsonArray.length()) {
            val item = albumsJsonArray.getJSONObject(index)
            val albumsArtistObject =
                albumsJsonArray.getJSONObject(index).getJSONObject(Constants.ALBUMS_ARTIST_KEY)


            val album = Album()
            album.albumID = item.get(Constants.TRACKS_ID) as Int
            album.albumTitle = item.get(Constants.TRACKS_TITLE) as String
            album.albumDuration = item.get(Constants.TRACKS_DURATION) as Int
            album.albumType = item.get(Constants.TRACKS_TYPE) as String
            album.albumPublishingDate = item.get(Constants.TRACKS_PUBLISHING_DATE) as String
            album.albumArtist = albumsArtistObject.get(Constants.TRACKS_ARTIST_NAME).toString()

            albumsList.add(album)
            tracksMap[Constants.ALBUMS_LIST] = albumsList

        }


    }

}