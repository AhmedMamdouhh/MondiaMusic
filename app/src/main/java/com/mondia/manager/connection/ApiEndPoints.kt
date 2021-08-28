package com.mondia.manager.connection

object ApiEndPoints {

    private const val BASE_URL = "http://staging-gateway.mondiamedia.com/"
    const val TOKEN = "${BASE_URL}v0/api/gateway/token/client"
    const val DATA_GROUPED = "${BASE_URL}v2/api/sayt/grouped"

    const val QUERY = "?query="
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Bearer "
    const val GATEWAY_KEY = "X-MM-GATEWAY-KEY"
    const val SECRET_KEY = "G2269608a-bf41-2dc7-cfea-856957fcab1e"
    const val CONTENT_TYPE = "Content-Type"
    const val CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded"
}