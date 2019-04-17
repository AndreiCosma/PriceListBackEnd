package com.csm.domain.dto

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
        val checked: Boolean
)