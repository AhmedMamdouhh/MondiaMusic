package com.mondia.manager.connection

interface WebServiceCallback {

    fun onWebServiceSuccess(data: HashMap<String, ArrayList<*>>)
    fun onWebServiceError(error: String)
}