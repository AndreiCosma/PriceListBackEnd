package com.csm.service.def

import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.User


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListItemService {
    fun createCheckListItem(user: User): CheckListItemDTO
    fun saveRemoteCreatedCheckListItem(checkListItemDTO: CheckListItemDTO, user: User)
    fun getCheckListItem(id: String, user: User): CheckListItemDTO
    fun getCheckListItems(checkListId: String, user: User): List<CheckListItemDTO>
    fun updateCheckListItem(checkListDTO: CheckListItemDTO, user: User)
    fun deleteCheckListItem(id: String, user: User)
}