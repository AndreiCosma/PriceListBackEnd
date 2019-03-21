package com.csm.service.def

import com.csm.domain.dto.CheckListDTO
import com.csm.domain.dto.CheckListItemDTO


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListItemService {
    fun createCheckList(): CheckListItemDTO
    fun saveRemoteCreatedCheckListItem(checkListItemDTO: CheckListItemDTO)
    fun getCheckListItem(id: Long): CheckListItemDTO
    fun getCheckListItems(checkListId: Long): List<CheckListItemDTO>
    fun updateCheckListItem(checkListDTO: CheckListItemDTO)
    fun deleteCheckListItem(id: Long)
}