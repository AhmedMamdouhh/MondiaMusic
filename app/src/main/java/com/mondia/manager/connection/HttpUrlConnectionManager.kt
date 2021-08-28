package com.mondia.manager.connection

import android.util.Log
import com.mondia.manager.utilities.Constants
import java.io.BufferedInputStream
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URL

object HttpUrlConnectionManager {

    private const val TAG = "HttpUrlConnection"
    private lateinit var connection: HttpURLConnection
    private var dataResponse: String = ""

    fun getHttpInstance(url: URL, requestMethod: String, token: String): String {

        try {
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = requestMethod

            if (requestMethod == Constants.GET) {
                connection.setRequestProperty(
                    ApiEndPoints.AUTHORIZATION,
                    ApiEndPoints.BEARER + token
                )
            } else {
                connection.setRequestProperty(
                    ApiEndPoints.GATEWAY_KEY,
                    ApiEndPoints.SECRET_KEY
                )
                connection.setRequestProperty(ApiEndPoints.CONTENT_TYPE, ApiEndPoints.CONTENT_TYPE_VALUE)
            }

            connection.useCaches = false
            connection.doInput = true
            connection.doOutput = false
            connection.connectTimeout = Constants.CONNECTION_TIME


            connection.connect()
        } catch (ce: ConnectException) {
            Log.e(TAG, "ConnectException: $ e.message")
            dataResponse = Constants.CONNECTION_ERROR
            return dataResponse
        } catch (e: IOException) {
            Log.e(TAG, "IOException: $ e.message")
            dataResponse = Constants.CONNECTION_ERROR
            return dataResponse
        } catch (e: Exception) {
            Log.e(TAG, "Exception: $ e.message")
            dataResponse = Constants.EXCEPTION
            return dataResponse
        }

        try {
            val bufferedInputStream = BufferedInputStream(connection.inputStream)
            val bufferedReader = bufferedInputStream.bufferedReader()
            val strBuffer = StringBuffer()

            for (line in bufferedReader.readLines()) {
                strBuffer.append(line)
            }

            bufferedReader.close()
            dataResponse = strBuffer.toString()
        } catch (e: Exception) {
            Log.e(TAG, "Exception d: $ e.message")
        }
        return dataResponse
    }

}