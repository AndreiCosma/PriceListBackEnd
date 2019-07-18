package com.csm.domain.dto

import java.util.*
import javax.validation.constraints.NotNull


/*
* Created by I503342 - 21/03/2019
*/
class CheckListItemDTO(
        @NotNull
        val listId: String,
        @NotNull
        val id: String,
        @NotNull
        val name: String,
        @NotNull
        val checked: Boolean,
        @NotNull
        val editDate: Date,
        @NotNull
        val position: Int
)