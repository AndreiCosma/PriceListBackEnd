package com.csm.domain.dto

import javax.validation.constraints.NotNull


/*
* Created by I503342 - 21/03/2019
*/
class CheckListItemDTO(
        @NotNull
        val listId: Long,
        @NotNull
        val id: Long,
        val name: String,
        val checked: Boolean
)