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

    fun getHttpInstance(url: URL): String {

        try {
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = Constants.GET
            connection.setRequestProperty(
                "Authorization",
                "Bearer " + "C3e3afd6e-bc93-40cd-9685-9f2dc81d2720"
            )
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

        val bufferedInputStream = BufferedInputStream(connection.inputStream)
        val bufferedReader = bufferedInputStream.bufferedReader()
        val strBuffer = StringBuffer()

        for (line in bufferedReader.readLines()) {
            strBuffer.append(line)
        }

        bufferedReader.close()

        dataResponse = strBuffer.toString()
        return dataResponse
    }

}