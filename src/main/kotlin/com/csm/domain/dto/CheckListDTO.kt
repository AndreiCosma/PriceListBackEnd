package com.csm.domain.dto

import javax.validation.constraints.NotNull


/*
* Created by I503342 - 21/03/2019
*/
class CheckListDTO(
        @NotNull
        val id: String,
        @NotNull
        val name: String,
        val items: MutableList<CheckListItemDTO>
)