package com.csm.domain.dto


/*
* Created by I503342 - 21/03/2019
*/
class CheckListDTO(
        val id: Long,
        val name: String,
        val items: MutableList<CheckListItemDTO>
)