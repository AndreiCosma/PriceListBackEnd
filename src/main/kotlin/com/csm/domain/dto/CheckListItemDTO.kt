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
        val name: String,
        val checked: Boolean
)