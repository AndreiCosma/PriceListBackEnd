package com.csm.service.def

import com.csm.domain.dto.CheckListItemDTO
import com.csm.domain.entity.User


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListItemService {
    fun createCheckList(user: User): CheckListItemDTO
    fun saveRemoteCreatedCheckListItem(checkListItemDTO: CheckListItemDTO, user: User)
    fun getCheckListItem(id: Long, user: User): CheckListItemDTO
    fun getCheckListItems(checkListId: Long, user: User): List<CheckListItemDTO>
    fun updateCheckListItem(checkListDTO: CheckListItemDTO, user: User)
    fun deleteCheckListItem(id: Long, user: User)
}