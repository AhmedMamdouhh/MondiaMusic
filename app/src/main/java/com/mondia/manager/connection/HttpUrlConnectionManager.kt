package com.mondia.manager.connection

import com.mondia.manager.utilities.Constants
import java.io.BufferedInputStream
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URL

object HttpUrlConnectionManager {

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
                    "X-MM-GATEWAY-KEY",
                    "G2269608a-bf41-2dc7-cfea-856957fcab1e"
                )
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            }

            connection.useCaches = false
            connection.doInput = true
            connection.doOutput = false
            connection.connectTimeout = Constants.CONNECTION_TIME


            connection.connect()
        } catch (ce: ConnectException) {
            dataResponse = Constants.CONNECTION_ERROR
            return dataResponse
        } catch (e: IOException) {
            dataResponse = Constants.CONNECTION_ERROR
            return dataResponse
        } catch (e: Exception) {
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
        }
        return dataResponse
    }

}