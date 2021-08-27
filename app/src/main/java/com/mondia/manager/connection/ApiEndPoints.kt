package com.mondia.manager.connection

object ApiEndPoints {

    const val BASE_URL = "http://staging-gateway.mondiamedia.com/"
    const val TOKEN = "${BASE_URL}v0/api/gateway/token/client"
    const val DATA_GROUPED = "${BASE_URL}v2/api/sayt/grouped"
    const val QUERY = "?query="
}