package com.csm.service.def

import org.springframework.stereotype.Service


/*
* Created by I503342 - 21/03/2019
*/
@Service
interface ClientService {
    fun init(clientName: String, clientSecret: String)
    fun checkClient(clientName: String, clientSecret: String)
    fun registerClient(clientName: String, clientSecret: String)
}