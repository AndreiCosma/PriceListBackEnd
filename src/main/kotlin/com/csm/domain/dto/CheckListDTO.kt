package com.csm.domain.dto

import java.util.*
import javax.persistence.Column
import javax.validation.constraints.NotNull


/*
* Created by I503342 - 21/03/2019
*/
class CheckListDTO(
        @NotNull
        val id: String,
        @NotNull
        val name: String,
        val items: MutableList<CheckListItemDTO>,
        val creationDate: Date,
        @NotNull
        val editDate: Date,
        @NotNull
        val position: Int
)