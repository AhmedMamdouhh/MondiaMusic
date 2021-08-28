package com.mondia.repository

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.mondia.manager.connection.ApiEndPoints
import com.mondia.manager.connection.HttpUrlConnectionManager
import com.mondia.manager.connection.WebServiceCallback
import com.mondia.manager.utilities.Constants
import org.json.JSONObject
import java.net.URL

class SplashRepository {

    private var responseResult: String = ""
    private var errorMessage: String = ""


    fun getUserToken(callback: WebServiceCallback) {

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                callback.onWebServiceError(errorMessage)
                //TODO: save response with sharedPref or handle connection between viewModel and repo.
            }
        }

        val thread: Thread = object : Thread() {
            override fun run() {
                try {

                    responseResult =
                        HttpUrlConnectionManager.getHttpInstance(
                            URL(ApiEndPoints.TOKEN),
                            Constants.POST,
                            ""
                        )

                } finally {
                    try {
                        if (responseResult == Constants.EXCEPTION || responseResult.isEmpty())
                            errorMessage = Constants.EXCEPTION
                        else if (responseResult == Constants.CONNECTION_ERROR)
                            errorMessage = Constants.CONNECTION_ERROR
                        else {
                            convertUserTokenJson(responseResult)
                        }
                    } finally {
                        handler.sendEmptyMessage(0)
                    }
                }
            }
        }
        thread.start()
    }

    private fun convertUserTokenJson(fullJson: String) {
        val jsonObject = JSONObject(fullJson)
        errorMessage = jsonObject.get("accessToken") as String
    }
}
