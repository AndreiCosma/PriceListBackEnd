package com.csm.service.def


/*
* Created by I503342 - 21/03/2019
*/

interface ClientService {
    fun checkClient(clientName: String, clientSecret: String)
}