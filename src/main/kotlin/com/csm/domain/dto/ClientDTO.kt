package com.csm.domain.dto

import javax.validation.constraints.NotNull


/*
* Created by I503342 - 21/03/2019
*/
data class ClientDTO(
        @NotNull
        val clientName: String,
        @NotNull
        val clientSecret: String
)