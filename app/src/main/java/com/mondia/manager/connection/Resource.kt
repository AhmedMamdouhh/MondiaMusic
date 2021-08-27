package com.mondia.manager.connection


class Resource<T> {

    var resourceStatus: ResourceStatus? = null

    var status = 0

    var data: T? = null

    var message: String? = null

    enum class ResourceStatus {
        SUCCESS, FAILED, NO_CONNECTION, LOADING, HIDE_LOADING
    }


}